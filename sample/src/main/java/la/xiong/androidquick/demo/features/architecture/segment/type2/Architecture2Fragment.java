package la.xiong.androidquick.demo.features.architecture.segment.type2;

import android.os.Bundle;
import android.view.View;

import com.androidwind.annotation.annotation.BindTag;
import com.androidwind.annotation.annotation.TagInfo;

import butterknife.OnClick;
import la.xiong.androidquick.demo.R;
import la.xiong.androidquick.demo.base.BaseFragment;
import la.xiong.androidquick.demo.features.architecture.segment.type2.module3.Module3;
import la.xiong.androidquick.tool.ToastUtil;

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
@BindTag(type = TagInfo.Type.FRAGMENT, tags = {"view", "module"}, description = "OneView + MultipleModules实例")
public class Architecture2Fragment extends BaseFragment implements IModuleCallback{
    private Module1 mModule1;
    private Module2 mModule2;
    private Module3 mModule3;// with MVP
    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initModule();
    }

    private void initModule() {
        View search_root = getActivity().findViewById(R.id.module_root);
        mModule1 = new Module1(search_root);
        mModule2 = new Module2(search_root, this);
        mModule3 = new Module3(search_root);

        BaseModule.initModulesView();
    }

    @Override
    public void onDestroy() {
        BaseModule.releaseModules();
        super.onDestroy();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_architecture2;
    }

    @OnClick({R.id.btn_module1, R.id.btn_module2, R.id.btn_module3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_module1:
                mModule1.hideView();
                break;
            case R.id.btn_module2:
                mModule2.modify("123");
                break;
            case R.id.btn_module3:// with MVP
                mModule3.toastSomething();
                break;
        }
    }

    @Override
    public void doModify(String content) {
        ToastUtil.showToast("Modify Content is: " + content);
    }

}
