// Generated code from Butter Knife. Do not modify!
package com.m.tripcard.ui.activity.findpassword;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class FindPasswordActivity$$ViewInjector {
  public static void inject(Finder finder, final com.m.tripcard.ui.activity.findpassword.FindPasswordActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099693, "field 'title'");
    target.title = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099654, "field 'timeTips'");
    target.timeTips = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099652, "field 'resetMail'");
    target.resetMail = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131099692, "field 'backBTN' and method 'onBTNCilck'");
    target.backBTN = (android.widget.TextView) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onBTNCilck(p0);
        }
      });
    view = finder.findRequiredView(source, 2131099653, "field 'resetPwdBTN' and method 'onBTNCilck'");
    target.resetPwdBTN = (android.widget.Button) view;
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onBTNCilck(p0);
        }
      });
  }

  public static void reset(com.m.tripcard.ui.activity.findpassword.FindPasswordActivity target) {
    target.title = null;
    target.timeTips = null;
    target.resetMail = null;
    target.backBTN = null;
    target.resetPwdBTN = null;
  }
}
