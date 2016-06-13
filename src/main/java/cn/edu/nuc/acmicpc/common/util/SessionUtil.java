package cn.edu.nuc.acmicpc.common.util;

import cn.edu.nuc.acmicpc.common.constant.SessionConstant;
import cn.edu.nuc.acmicpc.common.enums.AuthenticationType;
import cn.edu.nuc.acmicpc.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.sql.Timestamp;

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

    public static void updateContestPermission(Long contestId) {
        Session session = SecurityUtils.getSubject().getSession();
        String key = SessionConstant.CONTEST_PERMISSION_KEY + contestId;
        session.setAttribute(key, DateUtil.getCurrentTime());
    }

    public static Boolean checkContestPermission(Long contestId) {
        Session session = SecurityUtils.getSubject().getSession();
        String key = SessionConstant.CONTEST_PERMISSION_KEY + contestId;
        if (session.getAttribute(key) == null)
            return false;
        Timestamp loginTime = (Timestamp)session.getAttribute(key);
        Long timeDiff = DateUtil.getCurrentTime().getTime() - loginTime.getTime();
        if (timeDiff >= 0 && timeDiff <= SessionConstant.CONTEST_PERMISSION_INTERVAL) {
            updateContestPermission(contestId);
            return true;
        }
        return false;
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
