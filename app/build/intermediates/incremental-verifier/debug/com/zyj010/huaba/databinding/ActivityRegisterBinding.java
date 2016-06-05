package com.zyj010.huaba.databinding;
import com.zyj010.huaba.R;
import com.zyj010.huaba.BR;
import android.view.View;
public class ActivityRegisterBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.email_login_form, 1);
        sViewsWithIds.put(R.id.register_user, 2);
        sViewsWithIds.put(R.id.register_yanzheng, 3);
        sViewsWithIds.put(R.id.btn_send_code, 4);
        sViewsWithIds.put(R.id.register_password, 5);
        sViewsWithIds.put(R.id.finish_register, 6);
    }
    // views
    public final android.widget.Button btnSendCode;
    public final android.widget.LinearLayout emailLoginForm;
    public final android.widget.Button finishRegister;
    private final android.widget.LinearLayout mboundView0;
    public final android.widget.EditText registerPassword;
    public final android.widget.EditText registerUser;
    public final android.widget.EditText registerYanzheng;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRegisterBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.btnSendCode = (android.widget.Button) bindings[4];
        this.emailLoginForm = (android.widget.LinearLayout) bindings[1];
        this.finishRegister = (android.widget.Button) bindings[6];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.registerPassword = (android.widget.EditText) bindings[5];
        this.registerUser = (android.widget.EditText) bindings[2];
        this.registerYanzheng = (android.widget.EditText) bindings[3];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
        }
        return false;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityRegisterBinding>inflate(inflater, com.zyj010.huaba.R.layout.activity_register, root, attachToRoot, bindingComponent);
    }
    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityRegisterBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.zyj010.huaba.R.layout.activity_register, null, false), bindingComponent);
    }
    public static ActivityRegisterBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityRegisterBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_register_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityRegisterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}