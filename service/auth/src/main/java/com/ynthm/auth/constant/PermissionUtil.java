package com.ynthm.auth.constant;

/**
 * @author Ethan
 */
public class PermissionUtil {

  /**
   * 增加权限,可以一项或者多项|
   */
  public static int addPermission(int flag, int permission) {
    return flag | permission;
  }


  /**
   * 删除权限,可以一项或者多项|
   */
  public static int disablePermission(int flag, int permission) {
    return flag & ~permission;
  }

  /**
   * 是否拥有某些权限
   */
  public static boolean isAllow(int flag, int permission) {
    return (flag & permission) == permission;
  }

  /**
   * 是否不拥有某些权限
   */
  public static boolean isNotAllow(int flag, int permission) {
    return (flag & permission) == 0;
  }
}