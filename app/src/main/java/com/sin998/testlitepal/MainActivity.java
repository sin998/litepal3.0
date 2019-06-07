package com.sin998.testlitepal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sin998.testlitepal.bean.Student;

import org.litepal.LitePal;
import org.litepal.LitePalDB;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.tablemanager.callback.DatabaseListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "MainActivity";
    private Button mCreateStuBtn;
    private Button mUpdateStuByIdBtn;
    private Button mUpdateStuToDefaultBtn;
    private Button mDeleteStuByIdBtn;
    private Button mUpdateStuByConditionBtn;
    private Button mDeleteStuByConditionBtn;
    private Button mDeleteStuByDbNameBtn;
    private Button mQueryStuByIdBtn;
    private Button mQueryStuAllBtn;
    private Button mQueryStuByConditionBtn;
    private Button mDbOperationsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mCreateStuBtn = (Button) findViewById(R.id.create_stu_btn);
        mCreateStuBtn.setOnClickListener(this);
        mUpdateStuByIdBtn = (Button) findViewById(R.id.update_stu_by_id_btn);
        mUpdateStuByIdBtn.setOnClickListener(this);
        mUpdateStuToDefaultBtn = (Button) findViewById(R.id.update_stu_to_default_btn);
        mUpdateStuToDefaultBtn.setOnClickListener(this);
        mDeleteStuByIdBtn = (Button) findViewById(R.id.delete_stu_by_id_btn);
        mDeleteStuByIdBtn.setOnClickListener(this);
        mUpdateStuByConditionBtn = (Button) findViewById(R.id.update_stu_by_condition_btn);
        mUpdateStuByConditionBtn.setOnClickListener(this);
        mDeleteStuByConditionBtn = (Button) findViewById(R.id.delete_stu_by_condition_btn);
        mDeleteStuByConditionBtn.setOnClickListener(this);
        mDeleteStuByDbNameBtn = (Button) findViewById(R.id.delete_stu_by_db_name_btn);
        mDeleteStuByDbNameBtn.setOnClickListener(this);
        mQueryStuByIdBtn = (Button) findViewById(R.id.query_stu_by_id_btn);
        mQueryStuByIdBtn.setOnClickListener(this);
        mQueryStuAllBtn = (Button) findViewById(R.id.query_stu_all_btn);
        mQueryStuAllBtn.setOnClickListener(this);
        mQueryStuByConditionBtn = (Button) findViewById(R.id.query_stu_by_condition_btn);
        mQueryStuByConditionBtn.setOnClickListener(this);
        mDbOperationsBtn = (Button) findViewById(R.id.db_operations_btn);
        mDbOperationsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_stu_btn:
                Student createStudent = new Student();
                createStudent.setPname("sin998");
                createStudent.setPsex(0);
                createStudent.setPscore(50.00);
                if (createStudent.save()) {
                    Toast.makeText(this, "插入数据成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "插入数据失败", Toast.LENGTH_SHORT).show();
                }
                createStudent.saveAsync().listen(new SaveCallback() {
                    @Override
                    public void onFinish(boolean success) {
                        //TODO:插入完成后的操作
                    }
                });
                break;
            case R.id.update_stu_by_id_btn:
                Student updateStudentById = LitePal.find(Student.class, 1);
                updateStudentById.setPname("这是修改的名字");
                updateStudentById.setPsex(0);
                updateStudentById.setPscore(100.00);
                if (updateStudentById.save()) {
                    Toast.makeText(this, "更新数据成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "更新数据失败", Toast.LENGTH_SHORT).show();
                }
//                Student updateStudentById = new Student();
//                updateStudentById.setPname("这是更新的数据：我是直接new的student类");
//                updateStudentById.update(1);
//                updateStudentById.updateAsync(1);
                break;
            case R.id.update_stu_by_condition_btn:
                Student updateStudentByConditions = new Student();
                updateStudentByConditions.setPscore(100.00);
                updateStudentByConditions.updateAll("pname = ? and psex = ? ", "sin998", "1");
//                updateStudentByConditions.updateAllAsync("pname = ? and psex = ? ", "sin998", "1");
                break;
            case R.id.update_stu_to_default_btn:
//                Student updateStudentToDefault = new Student();
//                logdStuInfo(updateStudentToDefault);
//                updateStudentToDefault.setToDefault("pscore");
//                logdStuInfo(updateStudentToDefault);
//                updateStudentToDefault.setPscore(100);
//                logdStuInfo(updateStudentToDefault);
                Student updateStudentToDefault = new Student();
                updateStudentToDefault.setToDefault("pscore");
                logdStuInfo(updateStudentToDefault);
                int a = updateStudentToDefault.updateAll("pname = ? and psex = ? ", "sin998", "1");
                Log.d(TAG, "onClick: " + a);
//                updateStudentToDefault.updateAllAsync("pname = ? and psex = ? ", "sin998", "1");
                break;
            case R.id.delete_stu_by_id_btn:
                LitePal.delete(Student.class, 1);
//                LitePal.deleteAsync(Student.class, 1);
                break;
            case R.id.delete_stu_by_condition_btn:
                LitePal.deleteAll(Student.class, "pname = ?", "sin998");
//                LitePal.deleteAllAsync(Student.class,"pname = ?","sin998");
                break;
            case R.id.delete_stu_by_db_name_btn:
                LitePal.deleteDatabase("test");
                break;
            case R.id.query_stu_by_id_btn:
                Student student = LitePal.find(Student.class, 1);
                break;
            case R.id.query_stu_all_btn:
                List<Student> allStuList = LitePal.findAll(Student.class);
                break;
            case R.id.query_stu_by_condition_btn:
                List<Student> stuList = LitePal.where("name = ?", "sin998").order("id desc").find(Student.class);
//                List<Student> stuList = LitePal
//                        .where("name = ?","sin998")
//                        .order("id desc")
//                        .limit()
//                        .offset()
//                        .select()
//                        .sum()
//                        .max()
//                        .min()
//                        .findLast()
//                        .findFirst()
//                        .count()
//                        .average()
//                        .find(Student.class);
                break;
            case R.id.db_operations_btn:
                //运行时创建数据库
//                LitePalDB litePalDB = new LitePalDB("demo", 1);
//                litePalDB.addClassName(Student.class.getName());
//                LitePal.use(litePalDB);
                //与litepal.xml配置相同
//                LitePalDB litePalDB = LitePalDB.fromDefault("newdb");
//                LitePal.use(litePalDB);
                //切换回默认的数据库：
//                LitePal.useDefault();
                //删除数据库：
//                LitePal.deleteDatabase("test");
                //监听数据库创建/升级
//                LitePal.registerDatabaseListener(new DatabaseListener() {
//                    @Override
//                    public void onCreate() {
//                        // fill some initial data
//                    }
//
//                    @Override
//                    public void onUpgrade(int oldVersion, int newVersion) {
//                        // upgrade data in db
//                    }
//                });
                break;
        }
    }

    //此方法用于打印出传入的student类的值
    private void logdStuInfo(Student student) {
        Log.d(TAG, "logdStuInfo: id -> " + student.getId());
        Log.d(TAG, "logdStuInfo: pname -> " + student.getPname());
        Log.d(TAG, "logdStuInfo: psex -> " + student.getPsex());
        Log.d(TAG, "logdStuInfo: pscore -> " + student.getPscore());
    }

}
