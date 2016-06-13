package cn.edu.nuc.acmicpc.common.util;

import cn.edu.nuc.acmicpc.common.constant.SessionConstant;
import cn.edu.nuc.acmicpc.common.enums.AuthenticationType;
import cn.edu.nuc.acmicpc.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/3/21
 * Shiro session Util.
 */
public class SessionUtil {

    public static UserDto getCurrentLoginUser() {
        Session session = SecurityUtils.getSubject().getSession();
        UserDto currentLoginUser = (UserDto)session.getAttribute(
                SessionConstant.CURRENT_LOGIN_USER_KEY);
        return currentLoginUser;
    }

    public static Boolean isAdmin() {
        return SecurityUtils.getSubject().hasRole(AuthenticationType.ADMIN.getDescription());
    }

    public static Boolean checkContestPermission(Long contestId) {
        Session session = SecurityUtils.getSubject().getSession();
        String key = SessionConstant.CONTEST_PERMISSION_KEY + contestId;
        return session.getAttribute(key) != null ? true : false;
    }

    public static Byte getContestType(Long contestId) {
        Session session = SecurityUtils.getSubject().getSession();
        String key = SessionConstant.CONTEST_PERMISSION_KEY + contestId + "#type";
        return (Byte)session.getAttribute(key);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }
}
