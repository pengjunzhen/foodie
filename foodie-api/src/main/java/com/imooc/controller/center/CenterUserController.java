package com.imooc.controller.center;

import com.imooc.controller.BaseController;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.center.CenterUserBO;
import com.imooc.pojo.vo.UserVO;
import com.imooc.resource.FileUpload;
import com.imooc.service.center.CenterUserService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.DateUtil;
import com.imooc.utils.JSONResult;
import com.imooc.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.imooc.controller.BaseController.IMAGE_USER_FACE_LOCATION;

/**
 * @author pengjunzhen
 * @description
 * @date 2020/2/29 16:39
 */
@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CenterUserController.class);

    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private FileUpload fileUpload;

    @ApiOperation(value = "用户头像修改", notes = "用户头像修改", httpMethod = "POST")
    @PostMapping("uploadFace")
    public JSONResult uploadFace(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "file", value = "用户头像", required = true)
                    MultipartFile file,
            HttpServletRequest request, HttpServletResponse response) {

        // 定义头像保存的地址
//        String fileSpace = IMAGE_USER_FACE_LOCATION;
        String fileSpace = fileUpload.getImageUserFaceLocation();
        // 在路径上为每一个用户增加一个userid，用于区分不同用户上传
        String uploadPathPrefix = userId;

        // 开始文件上传
        if (file != null) {
            String fileName = file.getOriginalFilename();

            if (StringUtils.isNotBlank(fileName)){

                // 文件重命名 imooc-face.png -> ["imooc-face", "png"]
                String[] fileNameArr = fileName.split("\\.");
                LOGGER.info("fileUpload: {}", fileUpload);
                // 获取文件名后缀
                String suffix = fileNameArr[fileNameArr.length - 1];

                if (!suffix.equalsIgnoreCase("png")
                        && !suffix.equalsIgnoreCase("jpg")
                        && !suffix.equalsIgnoreCase("jpeg")) {
                    return JSONResult.errorMsg("图片格式不正确！");
                }

                // face-{userid}.png
                // 文件名称重组 覆盖上传，增量式：额外拼接当前时间
                String newFileName = "face-" + userId + "." + suffix;

                // 上传的头像最终保持的位置
                String finalFacePath = fileSpace + File.separator + uploadPathPrefix + File.separator + newFileName;
                // 用于提供给web服务访问的地址
                uploadPathPrefix = "/" + uploadPathPrefix +("/" + newFileName);
                File outFile = new File(finalFacePath);
                LOGGER.info("finalFacePath: {}", finalFacePath);
                LOGGER.info("uploadPathPrefix: {}", uploadPathPrefix);
                if (outFile.getParentFile() != null) {
                    // 创建文件夹
                    outFile.getParentFile().mkdirs();
                }

                try (FileOutputStream fileOutputStream = new FileOutputStream(outFile);
                     InputStream inputStream = file.getInputStream()) {

                    IOUtils.copy(inputStream, fileOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return JSONResult.errorMsg("文件不能为空");
        }

        // 获取图片服务地址
        String imageServerUrl = fileUpload.getImageServerUrl();

        // 由于浏览器可能存在缓存的情况，所以在这里，我们需要加上时间戳来保证更新后的图片可以及时刷新
        String finalUserFaceUrl = imageServerUrl + uploadPathPrefix
                + "?t=" + DateUtil.getCurrentDateString(DateUtil.DATE_PATTERN);

        // 更新用户头像到数据库
        Users userResult = centerUserService.updateUserFace(userId, finalUserFaceUrl);
//        userResult = setNullProperty(userResult);

        // 后续要改，增加令牌token，会整合进redis，分布式会话
        UserVO userVO = convertUserVo(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userVO), true);

        return JSONResult.ok();
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "POST")
    @PostMapping("update")
    public JSONResult update(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId,
            @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request,
            HttpServletResponse response) {

        // 判断BindingResult 是否保存错误的验证信息，如果有，则直接return
        if (result.hasErrors()) {
            Map<String, String> errorMap = getErrors(result);
            return JSONResult.errorMap(errorMap);
        }

        Users userResult = centerUserService.updateInfo(userId, centerUserBO);
//        userResult = setNullProperty(userResult);

        // 增加令牌token，会整合进redis，分布式会话
        UserVO userVO = convertUserVo(userResult);

        CookieUtils.setCookie(request, response, "user",
                JsonUtils.objectToJson(userVO), true);

        return JSONResult.ok();
    }

    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>(16);
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError fieldError : errorList) {
            // 发生错误所对应的某个属性
            String errorField = fieldError.getField();
            // 验证错误信息
            String defaultMessage = fieldError.getDefaultMessage();
            map.put(errorField, defaultMessage);
        }
        return map;
    }


    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }
}
