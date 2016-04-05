package cn.edu.nuc.acmicpc.common.check;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 16/4/3
 * Checker for file uploader or unzip tools.
 */
public interface Checker<E> {

    /**
     * Check certain entity, if the entity is invalid, throws an
     *
     */
    public void check(E entity);
}
