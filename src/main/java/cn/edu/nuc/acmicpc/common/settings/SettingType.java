package cn.edu.nuc.acmicpc.common.settings;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * setting type
 */
public enum SettingType {

    HOST(1), ENCODING(2), UPLOAD_FOLDER(3), PICTURE_FOLDER(4), JUDGE_CORE(5),
    DATA_PATH(6), WORK_PATH(7), JUDGES(8), EMAIL(9), RECORD_PER_PAGE(10);

    private Integer typeId;

    SettingType(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
