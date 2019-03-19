package ff9crystal.characters;

import java.util.ArrayList;

import basemod.animations.SpriterAnimation;
import java.util.Map.Entry;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ModHelper;
import ff9crystal.FF9CrystalMod;
//import ff9crystal.cards.starter.MinorHealing;
import ff9crystal.patches.AbstractCardEnum;
import ff9crystal.patches.CharacterEnum;

//import ff9crystal.relics.*;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FF9Character extends CustomPlayer{

    public static final int ENERGY_PER_TURN = 3;

    public static final String CHARACTER_IMG_PATH = FF9CrystalMod.IMG_PATH + "char/";
    public static final String MY_CHARACTER_SHOULDER_2 =  CHARACTER_IMG_PATH + "valiant_shoulder2.png"; // campfire pose
    public static final String MY_CHARACTER_SHOULDER_1 = CHARACTER_IMG_PATH + "valiant_shoulder.png"; // another campfire pose
    public static final String MY_CHARACTER_CORPSE = CHARACTER_IMG_PATH + "corpse_valiant.png"; // dead corpse
    public static final String MY_CHARACTER_SKELETON_ATLAS = CHARACTER_IMG_PATH + "idle/skeleton.atlas"; // spine animation atlas
    public static final String MY_CHARACTER_SKELETON_JSON = CHARACTER_IMG_PATH + "idle/skeleton.json"; // spine animation json

    private static final String ORB_PATH = CHARACTER_IMG_PATH + "orb/";
    private static final String FF9CHARACTER_LAYER1_PATH = ORB_PATH + "layer1.png";
    private static final String FF9CHARACTER_LAYER2_PATH = ORB_PATH + "layer2.png";
    private static final String FF9CHARACTER_LAYER3_PATH = ORB_PATH + "layer3.png";
    private static final String FF9CHARACTER_LAYER4_PATH = ORB_PATH + "layer4.png";
    private static final String FF9CHARACTER_LAYER5_PATH = ORB_PATH + "layer5.png";
    private static final String FF9CHARACTER_LAYER6_PATH = ORB_PATH + "layer6.png";
    private static final String FF9CHARACTER_LAYER1D_PATH = ORB_PATH + "layer1d.png";
    private static final String FF9CHARACTER_LAYER2D_PATH = ORB_PATH + "layer2d.png";
    private static final String FF9CHARACTER_LAYER3D_PATH = ORB_PATH + "layer3d.png";
    private static final String FF9CHARACTER_LAYER4D_PATH = ORB_PATH + "layer4d.png";
    private static final String FF9CHARACTER_LAYER5D_PATH = ORB_PATH + "layer5d.png";
    private static final String FF9CHARACTER_VFX_PATH = ORB_PATH + "vfx.png";

    private static final String ANIMATION_PATH = CHARACTER_IMG_PATH + "animation/Idle_final_sizetest.scml";

    public static final Logger logger = LogManager.getLogger(FF9Character.class.getName());

    public static final String[] orbTextures = {
            FF9CHARACTER_LAYER1_PATH
            ,FF9CHARACTER_LAYER2_PATH
            ,FF9CHARACTER_LAYER3_PATH
            ,FF9CHARACTER_LAYER4_PATH
            ,FF9CHARACTER_LAYER5_PATH
            ,FF9CHARACTER_LAYER6_PATH
            ,FF9CHARACTER_LAYER1D_PATH
            ,FF9CHARACTER_LAYER2D_PATH
            ,FF9CHARACTER_LAYER3D_PATH
            ,FF9CHARACTER_LAYER4D_PATH
            ,FF9CHARACTER_LAYER5D_PATH
    };

    public static final int STARTING_HP = 100;
    public static final int MAX_HP = 100;
    public static final int STARTING_GOLD = 80;
    public static final int HAND_SIZE = 5;

    public FF9Character (String name) {
        super(name, CharacterEnum.FF9Crystal,orbTextures ,FF9CHARACTER_VFX_PATH ,null, new SpriterAnimation(ANIMATION_PATH));

        this.dialogX = (this.drawX + 0.0F * Settings.scale); // set location for text bubbles
        this.dialogY = (this.drawY + 220.0F * Settings.scale); // you can just copy these values

        initializeClass(null, MY_CHARACTER_SHOULDER_2, // required call to load textures and setup energy/loadout
                MY_CHARACTER_SHOULDER_1,
                MY_CHARACTER_CORPSE,
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));

        loadAnimation(MY_CHARACTER_SKELETON_ATLAS, MY_CHARACTER_SKELETON_JSON, 1.0F); // if you're using modified versions of base game animations or made animations in spine make sure to include this bit and the following lines

        AnimationState.TrackEntry e = this.state.setAnimation(0, "animation", true);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public String getLocalizedCharacterName() {
        return "FF9 Crystal";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new FF9Character("FF9 Crystal");
    }

    public ArrayList<String> getStartingDeck() { // starting deck 'nuff said
        ArrayList<String> retVal = new ArrayList<>();
//        retVal.add("MyCard0");
//        retVal.add("MyCard0");
//        retVal.add("MyCard0");
//        retVal.add("MyCard0");
//        retVal.add("MyCard1");
//        retVal.add("MyCard1");
//        retVal.add("MyCard1");
//        retVal.add("MyCard1");
//        retVal.add("MyCard2");
        return retVal;
    }

    @Override
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        AbstractCard card = null;
        for (Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
            card = (AbstractCard) c.getValue();
            if ((card.color == AbstractCardEnum.Crystal) && (card.rarity != AbstractCard.CardRarity.BASIC)
                    && ((!UnlockTracker.isCardLocked((String) c.getKey())) || (Settings.treatEverythingAsUnlocked()))) {
                tmpPool.add(card);
            }
        }
        if (ModHelper.isModEnabled("Red cards")) {
            CardLibrary.addRedCards(tmpPool);
        }
        if (ModHelper.isModEnabled("Green cards")) {
            CardLibrary.addGreenCards(tmpPool);
        }
        if (ModHelper.isModEnabled("Blue cards")) {
            CardLibrary.addBlueCards(tmpPool);
        }
        if (ModHelper.isModEnabled("Colorless cards")) {
            CardLibrary.addColorlessCards(tmpPool);
        }
        return tmpPool;
    }

    public ArrayList<String> getStartingRelics() { // starting relics - also simple
        ArrayList<String> retVal = new ArrayList<>();
//        retVal.add("MyRelic");
//        UnlockTracker.markRelicAsSeen("MyRelic");
        return retVal;
    }

    public CharSelectInfo getLoadout() { // the rest of the character loadout so includes your character select screen info plus hp and starting gold
        return new CharSelectInfo("FF9 Crystal", "The magical crystal from the world of Final Fantasy 9. Able to use all abilities of the cast.",
                STARTING_HP, MAX_HP,0, STARTING_GOLD, HAND_SIZE,
                this, getStartingRelics(), getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return playerClass.name();
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.Crystal;
    }

    @Override
    public Color getCardRenderColor() {
        return Color.BLUE.cpy();
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Strike_Blue();
    }

    @Override
    public Color getCardTrailColor() {
        return Color.BLUE.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 10;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_IRON_2", MathUtils.random(-0.2F, 0.2F));
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_IRON_2";
    }

    @Override
    public String getSpireHeartText() {
        return "NL You ready your Weapon...";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.BLUE.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL
                , AbstractGameAction.AttackEffect.SLASH_HORIZONTAL
                , AbstractGameAction.AttackEffect.SLASH_VERTICAL
                , AbstractGameAction.AttackEffect.SLASH_HEAVY
        };
    }

    //TODO: characters Specific Dialog
    @Override
    public String getVampireText() {
        return "Navigating an unlit street, you come across several hooded figures in the midst of some dark ritual. As you approach, they turn to you in eerie unison. The tallest among them bares fanged teeth and extends a long, pale hand towards you. NL ~\"Join~ ~us,~ ~oh Mighty Warrior,~ ~and~ ~feel~ ~the~ ~warmth~ ~of~ ~the~ ~Spire.\"~";
    }
}
