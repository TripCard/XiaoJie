// Generated code from Butter Knife. Do not modify!
package com.m.tripcard.ui.activity.login;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginActivity$$ViewInjector {
  public static void inject(Finder finder, final com.m.tripcard.ui.activity.login.LoginActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099663, "field 'passwordEdit'");
    target.passwordEdit = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131099665, "field 'registerBTN' and method 'onMenuClick'");
    target.registerBTN = (android.widget.TextView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onMenuClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099656, "field 'loginQQ' and method 'onMenuClick'");
    target.loginQQ = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onMenuClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099657, "field 'loginWeibo' and method 'onMenuClick'");
    target.loginWeibo = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onMenuClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099661, "field 'userNameEdit'");
    target.userNameEdit = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131099666, "field 'forgetPwdBTN' and method 'onMenuClick'");
    target.forgetPwdBTN = (android.widget.TextView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onMenuClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099664, "field 'loginBTN' and method 'onMenuClick'");
    target.loginBTN = (android.widget.ImageView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onMenuClick(p0);
        }
      });
  }

  public static void reset(com.m.tripcard.ui.activity.login.LoginActivity target) {
    target.passwordEdit = null;
    target.registerBTN = null;
    target.loginQQ = null;
    target.loginWeibo = null;
    target.userNameEdit = null;
    target.forgetPwdBTN = null;
    target.loginBTN = null;
  }
}
