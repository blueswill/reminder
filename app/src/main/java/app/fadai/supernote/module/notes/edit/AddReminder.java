package app.fadai.supernote.module.notes.edit;
//editnoteactivity addreminder  create_detail_journal
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import com.app.fadai.supernote.R;
import com.orhanobut.logger.Logger;

import app.fadai.supernote.bean.ImageEntity;
import app.fadai.supernote.module.base.BaseActivity;
import app.fadai.supernote.utils.PermissionUtils;
import app.fadai.supernote.utils.ProgressDialogUtils;
import app.fadai.supernote.widget.MyEditText;
import butterknife.BindView;

public class AddReminder extends BaseActivity<IEditNoteView, EditNotePresenter> implements IEditNoteView, View.OnClickListener{
    // TableLayout，用于让EditText失去焦点
    protected View tableTl;

    // 标题栏左侧的Ib
    protected ImageButton titleLeftIb;
    // 标题栏中间的Tv
    protected TextView titleMiddleTv;
    // 标题栏的右侧Ib
    protected ImageButton titleRightIb;

    // 日程数据是否被修改
    boolean isChanged = false;

    // 日程输入栏
    protected LinearLayout contentInputLl;
    // 日程完成圆圈
    protected ImageView scheduleStatusImageIv;
    // 日程内容et
    protected EditText createScheduleContentEt;
    // 日程内容语音听写ib
    protected ImageButton createScheduleContentDictationIb;

    // 设置日程的标签
    protected TableRow createScheduleSetTagTr;
    // 显示日程标签的Tv
    protected TextView createScheduleTagTv;

    // 选择日程日期的tr
    protected TableRow createScheduleSetDateTr;
    // 显示选择的日期的tv
    protected TextView creatScheduleDateTv;

    // 选择日程时间的tr
    protected TableRow createScheduleSetTimeTr;
    // 显示选择的时间的tv
    protected TextView creatScheduleTimeTv;

    // 设置日程的提醒方式
    protected TableRow creatScheduleSetAlarmModeTr;
    // 显示日程的提醒方式
    protected TextView createScheduleAlarmModeTv;

    // 设置日程的重复模式
    protected TableRow createScheduleSetRepeatModeTr;
    // 显示日程的重复模式
    protected TextView createScheduleRepeatModeTv;

    // 日程的重复截止日期设置
    protected TableRow createScheduleSetRepeatCutOffDateTr;
    // 日程的重复截止日期显示
    protected TextView createScheduleRepeatCutOffDateTv;

    // 日程的备注输入
    protected EditText createScheduleAddRemarkEt;

    // 日程当前状态的显示
    protected TextView createScheduleStatusTv;

    // 设置日程的地点按钮
    protected ImageButton createScheduleSetLocationIb;

    // 当前选择的日程标签index
    protected int selectedTagIndex = 0;
    // 当前选择的日程提醒方式index
    protected int selectedAlarmModeIndex;
    // 当前选择的日程的重复模式的index
    protected int selectedRepeatModeIndex;


    @BindView(R.id.scroll_edit_note)
    ScrollView mScrollView;

    @BindView(R.id.et_edit_note_content)
    MyEditText mEdContent;

    @BindView(R.id.ll_edit_note_to_camera)
    LinearLayout mLlToCamera;

    @BindView(R.id.ll_edit_note_to_photo)
    LinearLayout mLlToPhoto;

    //new add
    @BindView(R.id.ll_edit_note_to_reminder)
    LinearLayout mLlToReminder;



    private ProgressDialogUtils mProgressDialogUtils = new ProgressDialogUtils(this);

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_create_detail_journal;
    }

    @Override
    protected EditNotePresenter initPresenter() {
        EditNotePresenter presenter = new EditNotePresenter();
        presenter.attch(this);
        return presenter;
    }

    @Override
    protected void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEdContent.setMinHeight(mPresenter.getNoteEditNeedHeight());
        mPresenter.initData();
        mLlToCamera.setOnClickListener(this);
        mLlToPhoto.setOnClickListener(this);
        mEdContent.setOnClickListener(this);




        ///new import

        tableTl = findViewById(R.id.table_tl);
        tableTl.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                tableTl.setFocusable(true);
                tableTl.setFocusableInTouchMode(true);
                tableTl.requestFocus();
                return false;
            }
        });



        createScheduleContentEt = (EditText) findViewById(R.id.create_schedule_content_et);

        createScheduleSetDateTr = (TableRow) findViewById(R.id.create_schedule_set_date_tr);
        creatScheduleDateTv = (TextView) findViewById(R.id.create_schedule_date_tv);

        createScheduleSetTimeTr = (TableRow) findViewById(R.id.create_schedule_set_time_tr);
        creatScheduleTimeTv = (TextView) findViewById(R.id.create_schedule_time_tv);

        creatScheduleSetAlarmModeTr = (TableRow) findViewById(R.id.create_schedule_set_alarm_mode_tr);
        createScheduleAlarmModeTv = (TextView) findViewById(R.id.create_schedule_alarm_mode_tv);

        createScheduleSetRepeatModeTr = (TableRow) findViewById(R.id.create_schedule_set_repeat_mode_tr);
        createScheduleRepeatModeTv = (TextView) findViewById(R.id.create_schedule_repeat_mode_tv);

        createScheduleSetRepeatCutOffDateTr = (TableRow) findViewById(R.id.create_schedule_set_repeat_cut_off_date_tr);
        createScheduleRepeatCutOffDateTv = (TextView) findViewById(R.id.create_schedule_repeat_cut_off_date_tv);






        createScheduleContentDictationIb.setOnClickListener(this);
        createScheduleSetTagTr.setOnClickListener(this);
        createScheduleSetDateTr.setOnClickListener(this);
        createScheduleSetTimeTr.setOnClickListener(this);
        creatScheduleSetAlarmModeTr.setOnClickListener(this);
        createScheduleSetRepeatModeTr.setOnClickListener(this);
        createScheduleSetRepeatCutOffDateTr.setOnClickListener(this);
        createScheduleSetLocationIb.setOnClickListener(this);
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            Logger.d("image:"+mEdContent.mImageList.get(0).getStart()+"  "+mEdContent.mImageList.get(0).getEnd());

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Logger.d("text change:" + s + "  " + start + "  " + count + "  " + before);
            for (int i = 0; i < mEdContent.mImageList.size(); i++) {
                ImageEntity imageEntity = mEdContent.mImageList.get(i);
                if (start == imageEntity.getEnd()) {
                    mEdContent.getEditableText().replace(imageEntity.getStart(), imageEntity.getEnd(), "");
                    mEdContent.mImageList.remove(i);
                    mEdContent.mDeleteImageList.add(imageEntity);
                    break;
                }
            }
            mEdContent.setTextCountChange(start, before, count);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    protected void updateViews() {
        mPresenter.parseNoteContent();
        mEdContent.setSelection(mEdContent.getText().length());
        mEdContent.addTextChangedListener(mTextWatcher);
    }

    @Override
    public Intent getActivityIntent() {
        return getIntent();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle("");       // 主标题为空
        getSupportActionBar().setSubtitle(title); // 副标题
    }

    @Override
    public void showNoteContent(String content) {
        mEdContent.setText(content);
    }

    @Override
    public void replaceImage(String imageName, Bitmap bitmap) {
        mEdContent.replaceDrawable(bitmap, imageName);
    }

    @Override
    public void insertImage(String imageName, Bitmap bitmap) {
        mEdContent.insertDrawable(bitmap, imageName);
    }

    @Override
    public void deleteImage(ImageEntity imageEntity) {
        mEdContent.getEditableText().replace(imageEntity.getStart(), imageEntity.getEnd()+1, "");
    }

    @Override
    public void setResultAndFinish(Intent intent) {
        setResult(RESULT_OK, intent);
    }

    @Override
    public void showLoading(String message) {
        mProgressDialogUtils.show(message);
    }

    @Override
    public void unShowLoading() {
        mProgressDialogUtils.hide();
    }

    @Override
    public void showToAppSettingDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle("权限设置")
                .setMessage("您已禁止应用的储存权限，请前往应用设置中开启")
                .setPositiveButton("前往",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PermissionUtils.toAppSetting(mContext);
                            }
                        })
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public void showStatisticsDialog(int imageCount, int textCount) {
        new AlertDialog.Builder(mContext)
                .setMessage("文字数量：" + textCount + "\n" + "图片数量：" + imageCount)
                .setPositiveButton("确定", null)
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_edit_note_to_camera:
                mPresenter.checkPermissionAndToCamera(mContext);
                break;
            case R.id.ll_edit_note_to_photo:
                mPresenter.checkPermissionAndToPhoto(mContext);
                break;
            case R.id.ll_edit_note_to_reminder:
                mPresenter.DealToReminder(mContext);
            case R.id.et_edit_note_content:
                mPresenter.clickNoteEditText(mEdContent);
                break;


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_note_statistics:
                mPresenter.calculateContentAndImageCount(mEdContent);
                break;
            case R.id.menu_note_share:
                showShareDialg();
                break;
        }
        return true;
    }

    /**
     *   显示分享Dialog
     */
    private void showShareDialg() {

        if(mEdContent.getText().length()==0){
            return;
        }

        setEditTextBeforeGetBitmap();
        String items[];

        // 没有图片时 添加：以文字形式分享的方法
        if(mEdContent.mImageList.size()==0){
            items=new String[]{"以图片形式分享","以文字形式分享"};
        } else{
            items=new String[]{"以图片形式分享"};
        }

        final AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setTitle("分享")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                mPresenter.shareNoteWithImage(mEdContent);
                                break;
                            case 1:
                                shareText(mEdContent.getText().toString());
                                setEditTextAfterGetBitmap();
                                break;
                        }
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        setEditTextAfterGetBitmap();
                    }
                })
                .show();
    }

    public void shareText(String content){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("text/plain");
        intent = Intent.createChooser(intent, "分享");
        startActivity(intent);
    }

    @Override
    public void setEditTextBeforeGetBitmap(){
        mPresenter.closeKeyboard(mEdContent);
        mEdContent.setMinHeight(0);
        mEdContent.setEnabled(false);
    }

    @Override
    public void setEditTextAfterGetBitmap(){
        mEdContent.setMinHeight(mPresenter.getNoteEditNeedHeight());
        mEdContent.setEnabled(true);
    }



    @Override
    public void onBackPressed() {
        mPresenter.saveNote(mEdContent.getText().toString());
        super.onBackPressed();
    }

}