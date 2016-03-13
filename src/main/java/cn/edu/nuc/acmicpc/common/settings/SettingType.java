package cn.edu.nuc.acmicpc.common.settings;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * setting type
 */
public enum SettingType {

    HOST(1L), ENCODING(2L), UPLOAD_FOLDER(3L), PICTURE_FOLDER(4L), JUDGE_CORE(5L),
    DATA_PATH(6L), WORK_PATH(7L), JUDGES(8L), EMAIL(9L), RECORD_PER_PAGE(10L);

    private Long typeId;

    SettingType(Long typeId) {
        this.typeId = typeId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
}
