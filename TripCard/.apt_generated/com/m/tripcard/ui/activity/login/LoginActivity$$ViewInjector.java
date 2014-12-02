// Generated code from Butter Knife. Do not modify!
package com.m.tripcard.ui.activity.login;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginActivity$$ViewInjector {
  public static void inject(Finder finder, final com.m.tripcard.ui.activity.login.LoginActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2130968580, "field 'loginWeibo' and method 'loginByWeibo'");
    target.loginWeibo = (android.widget.TextView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.loginByWeibo();
        }
      });
    view = finder.findRequiredView(source, 2130968579, "field 'loginQQ' and method 'loginByQQ'");
    target.loginQQ = (android.widget.TextView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.loginByQQ();
        }
      });
  }

  public static void reset(com.m.tripcard.ui.activity.login.LoginActivity target) {
    target.loginWeibo = null;
    target.loginQQ = null;
  }
}
