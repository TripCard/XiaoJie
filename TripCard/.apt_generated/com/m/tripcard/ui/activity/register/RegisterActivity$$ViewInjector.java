// Generated code from Butter Knife. Do not modify!
package com.m.tripcard.ui.activity.register;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class RegisterActivity$$ViewInjector {
  public static void inject(Finder finder, final com.m.tripcard.ui.activity.register.RegisterActivity target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131099693, "field 'title'");
    target.title = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131099692, "field 'backBTN'");
    target.backBTN = (android.widget.TextView) view;
  }

  public static void reset(com.m.tripcard.ui.activity.register.RegisterActivity target) {
    target.title = null;
    target.backBTN = null;
  }
}
