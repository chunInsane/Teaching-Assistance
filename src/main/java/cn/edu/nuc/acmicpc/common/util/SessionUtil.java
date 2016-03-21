package cn.edu.nuc.acmicpc.common.util;

import cn.edu.nuc.acmicpc.common.constant.SessionConstant;
import cn.edu.nuc.acmicpc.common.enums.AuthenticationType;
import cn.edu.nuc.acmicpc.dto.UserDto;

import javax.servlet.http.HttpSession;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 */
public class SessionUtil {

    public static UserDto getCurrentLoginUser(HttpSession session) {
        UserDto currentLoginUser = (UserDto)session.getAttribute(
                SessionConstant.CURRENT_LOGIN_USER_KEY);
        return currentLoginUser;
    }

    public static Boolean isAdmin(HttpSession session) {
        UserDto userDto = getCurrentLoginUser(session);
        return userDto != null && userDto.getType() == AuthenticationType.ADMIN.ordinal();
    }
}
