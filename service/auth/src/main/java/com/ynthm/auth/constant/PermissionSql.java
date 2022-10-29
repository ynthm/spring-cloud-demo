package com.ynthm.auth.constant;


/**
 * @author Ethan
 */
public class PermissionSql {
  private static final int ALLOW_SELECT = 1 << 0;
  private static final int ALLOW_INSERT = 1 << 1;
  private static final int ALLOW_UPDATE = 1 << 2;
  private static final int ALLOW_DELETE = 1 << 3;
}