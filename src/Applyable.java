@FunctionalInterface
public interface Applyable {
    boolean applyStatusEffect(Fighter f);
    //true means it applied (good for skip turns)
    //false means it did not apply
}
