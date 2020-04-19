
class Card
{
    private DisplayComponent display;
    private PowerComponent power;
    private AbilityComponent ability;

    public Card(String name, String desc)
    {
        display = new DisplayComponent(name, desc);
    }

    public DisplayComponent getDisplayComponent() { return display; }
    public PowerComponent getPowerComponent() { return power; }
    public AbilityComponent getAbilityComponent() { return ability; }
}

class CardPosition
{
    private boolean attMode = false;
    private boolean open = false;

    public CardPosition(boolean attMode, boolean open)
    {
        this.attMode = attMode;
        this.open = open;
    }

    public boolean isAttMode() { return attMode; }
    public boolean isOpen() { return open; }
}

class Field
{
    protected Card SummonedCard;
    protected CardPosition position;

    public Field() { }
    public void cardRemoved()
    {
        SummonedCard = null;
        position = null;
    }
}

class PlaceCardOnField extends Field
{
    Field field;
    private boolean attMode;
    private boolean open; 

    public PlaceCardOnField(Field field, Card card)
    {
        this.field = field;
        SummonedCard = card;
    }

    public PlaceCardOnField attMode() 
    {
        attMode = true;
        return this;
    }
    public PlaceCardOnField defMode() 
    {
        attMode = false;
        return this;
    }
    public PlaceCardOnField open()
    {
        attMode = false;
        return this;
    }
    public PlaceCardOnField close()
    {
        attMode = false;
        return this;
    }

    public void place() 
    {
        position = new CardPosition(attMode, open);
        field = this;
    }
}

enum Element { WATER, FIRE, EARTH, AIR }

class DisplayComponent
{
    private String name;
    private String desc;

    public DisplayComponent(String name, String desc)
    {
        this.name = name;
        this.desc = desc;
    }

    public String getName() { return name; }
    public Sring getDesc() { return desc; }
}

class PowerComponent
{
    private Element el;
    private int power;

    public PowerComponent (String el, int power)
    {
        this.el = Element.valueOf(el);
        this.power = power;
    }

    public Element getEl() { return el; }
    public int getPower() { return power; }
}

class AbilityComponent
{
    Skill skill;

    public AbilityComponent(Skill skill)
    {
        this.skill = skill;
    }

    public Skill getSkill() { return skill; }
}

abstract class Skill implements Action
{
    public abstract void onAction();
}

interface Action
{
    public void onAction();
}

interface Attack 
{
    public void setAtt(Field att);
}

interface SingleTarget
{
    public void setTarget(Field target);
}

interface AttackPlayer
{
    public void setTargetPlayer();
}

class SimpleAttack extends Action implements Attack, SingleTarget, AttackPlayer
{
    Field att;
    Field target;
    Player playerTarget;

    public void setAtt(Field att) { this.att = att; }
    public void setTarget(Field target) { this.target = target; }
    public void setTargetPlayer(Player playerTarget) { this.playerTarget = playerTarget; }
    public void onAction()
    {

    }
}

class DestroySkill extends Skill implements Attack, SingleTarget
{
    Field att;
    Field target;

    public void setAtt(Field att) { this.att = att; }
    public void setTarget(Field target) { this.target = target; }
    public void onAction() { target.cardRemoved(); }
}
class Aura extends Action { }
class PowerUp extends Action { }










