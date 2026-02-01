public class StatusEffect {
    private final String name;
    private final String description;
    private final int duration;
    private int turnsHeld;
    private final MoveType targetType;
    private final boolean isBuff;
    private final ScalarModifyable scalarModifier;
    private final Applyable statusEffectApplier;


    public StatusEffect(String n, String d, int dur, MoveType type, boolean isBuff, ScalarModifyable sca, Applyable sta) {
        this.name = n;
        this.description = d;
        this.duration = dur;
        this.targetType = type;
        this.isBuff = isBuff;
        this.scalarModifier = sca;
        this.statusEffectApplier = sta;
        turnsHeld = 0;
    }

    public String getName() {return name;}
    public String getDescription() {return description;}
    public MoveType getTargetType() {return targetType;}
    public int getTurnsHeld() {return turnsHeld;}
    public int getDuration() {return duration;}
    public boolean isBuff() {return isBuff;}
    public StatusEffect copy() {return new StatusEffect(name, description, duration, targetType, isBuff, scalarModifier, statusEffectApplier);}

    public boolean checkIfStatusFinished() {
        return duration <= turnsHeld;
    }
    public void resetTurnsHeld() {
        turnsHeld = 0;
    }
    public void proc() {
        turnsHeld++;
    }
    public boolean apply(Fighter f) {
        if (statusEffectApplier == null) return true;
        return statusEffectApplier.applyStatusEffect(f);
    }
    public double modify(double m) {
        if (scalarModifier == null) return m;
        return scalarModifier.modifyScalar(m);
    }


}
