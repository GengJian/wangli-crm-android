package customer.anim;


/**
 * 动画数据操作接口
 *
 */
public interface IAnimation {
    //更新数据
    void refresh(Anim anim);
    //判断动画是否结束
    boolean isOver(Anim anim);
}
