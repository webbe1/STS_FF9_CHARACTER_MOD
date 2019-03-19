package ff9crystal;

import com.megacrit.cardcrawl.core.CardCrawlGame;
//import ff9crystal.cards.common.attack.*;
//import ff9crystal.cards.common.skill.*;
//import ff9crystal.cards.rare.attack.*;
//import ff9crystal.cards.rare.power.*;
//import ff9crystal.cards.rare.skill.*;
//import ff9crystal.cards.starter.BlindingLight;
//import ff9crystal.cards.starter.Defend_Valiant;
//import ff9crystal.cards.starter.MinorHealing;
//import ff9crystal.cards.starter.Strike_Valiant;
//import ff9crystal.cards.uncommon.attack.*;
//import ff9crystal.cards.uncommon.power.*;
import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import ff9crystal.cards.uncommon.skill.*;
import ff9crystal.characters.FF9Character;
import ff9crystal.patches.AbstractCardEnum;
import ff9crystal.patches.CharacterEnum;
//import ff9crystal.relics.*;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class FF9CrystalMod implements EditCharactersSubscriber{

    private static final Color CRYSTAL = CardHelper.getColor(0.0f, 125.0f, 250.0f);

    private static final String Dev = "AiramiDrk";

    public static final String RESOURCE_PATH = "ff9crystal/";
    public static final String IMG_PATH = RESOURCE_PATH + "img/";
    public static final String LOCALIZATION_PATH = RESOURCE_PATH + "localization/";
    public static final String CARD_STRINGS = LOCALIZATION_PATH+ "ValiantMod-CardStrings.json";
    public static final String POWER_STRINGS = LOCALIZATION_PATH + "ValiantMod-PowerStrings.json";
    public static final String RELIC_STRINGS = LOCALIZATION_PATH + "ValiantMod-RelicStrings.json";

    //ValiantAssets backgrounds
    private static final String ATTACK_WHITE = IMG_PATH + "512/bg_attack_holy_512.png";
    private static final String SKILL_WHITE = IMG_PATH + "512/bg_skill_holy_512.png";
    private static final String POWER_WHITE = IMG_PATH + "512/bg_power_holy_512.png";
    private static final String ENERGY_ORB_CRYSTAL = IMG_PATH + "512/card_holy_orb.png";

    private static final String ATTACK_WHITE_PORTRAIT = IMG_PATH + "1024/bg_attack_holy_1024.png";
    private static final String SKILL_WHITE_PORTRAIT = IMG_PATH + "1024/bg_skill_holy_1024.png";
    private static final String POWER_WHITE_PORTRAIT = IMG_PATH + "1024/bg_power_holy_1024.png";
    private static final String ENERGY_ORB_CRYSTAL_PORTRAIT = IMG_PATH + "1024/card_holy_orb.png";

    public static final String CARD_IMG_PATH = IMG_PATH + "cards/";
    public static final String PLACE_HOLDER_CARD_PATH = "corona.png";
    public static final String STRIKE_V = CARD_IMG_PATH + "Strike.png";
    public static final String DEFEND_V = CARD_IMG_PATH + "Defend.png";
    public static final String COMBAT_DANCE = CARD_IMG_PATH + "Combat Dance.png";
    public static final String DECIMATE = CARD_IMG_PATH + "Decimate.png";
    public static final String DIVINE_LASHES = "comet.png";
    public static final String EMPOWERING_FORCE = CARD_IMG_PATH + "Empowering Force.png";
    public static final String GRACEFUL_SIPHON = CARD_IMG_PATH + "Debilitation.png";
    public static final String MERCILESS_SMITE = CARD_IMG_PATH + "Merciless Smite.png";
    public static final String PIERCING_STAB = CARD_IMG_PATH + "Piercing Stab.png";
    public static final String POWER_STRIKE = CARD_IMG_PATH + "brainstorm.png";
    public static final String QUICK_SIPHON = CARD_IMG_PATH + "Quick Siphon.png";
    public static final String RECKLESS_SMITE = CARD_IMG_PATH + "Reckless Smite.png";
    public static final String SMITE = CARD_IMG_PATH + "Smite.png";
    public static final String TOWERING_CHARGE = CARD_IMG_PATH + "Towering Charge.png";
    public static final String WIDE_SMITE = CARD_IMG_PATH + "Wide Smite.png";
    public static final String ARCANE_HEALING = CARD_IMG_PATH + "Arcane Healing.png";
    public static final String DIVINE_FAVOR = CARD_IMG_PATH + "Divine Favor.png";
    public static final String FLASH_OF_ANGER = CARD_IMG_PATH + "Flash of Anger.png";
    public static final String HOLY_FURY = CARD_IMG_PATH + "Holy Fury.png";
    public static final String ITS_SMITING_TIME = CARD_IMG_PATH + "Its Smiting Time.png";
    public static final String MIGHT_BRACER = CARD_IMG_PATH + "Might Bracer.png";
    public static final String POWER_STANCE = CARD_IMG_PATH + "power Stance.png";
    public static final String QUICKSTEP = CARD_IMG_PATH + "Quickstep.png";
    public static final String SEEK_ANSWERS = CARD_IMG_PATH + "Seek Answers.png";
    public static final String TAKE_AIM = CARD_IMG_PATH + "Take Aim.png";
    public static final String UNYIELDING_ZEAL = CARD_IMG_PATH + "Unyielding Zeal.png";
    public static final String ZEALOUS_PERSECUTION = CARD_IMG_PATH + "Zealous Persecution.png";
    public static final String ALMIGHTY_SMITE = CARD_IMG_PATH + "Almighty Smite.png";
    public static final String DANGEROUS_SMASH = CARD_IMG_PATH + "Exhausting Smash.png";
    public static final String EXECUTION = CARD_IMG_PATH + "Execution.png";
    public static final String FINAL_GAMBIT = CARD_IMG_PATH + "Final Gambit.png";
    public static final String HOLY_STORM = CARD_IMG_PATH + "DEUS VULT.png";
    public static final String TANTRUM = CARD_IMG_PATH + "Tantrum.png";
    public static final String FULL_AWAKENING = CARD_IMG_PATH + "Full Awakening.png";
    public static final String OVERBURN = CARD_IMG_PATH + "Overburn.png";
    public static final String PEACEFUL_STANCE = CARD_IMG_PATH + "Peaceful Stance.png";
    public static final String WEAK_FORM = CARD_IMG_PATH + "Weak Form.png";
    public static final String AT_ALL_COSTS = CARD_IMG_PATH + "At All Costs.png";
    public static final String BOMBASTIC = CARD_IMG_PATH + "Bombastic.png";
    public static final String DEADLY_PLAY = CARD_IMG_PATH + "Deadly Play.png";
    public static final String SALVATION = CARD_IMG_PATH + "Salvation.png";
    public static final String TEMPO_MASTER = CARD_IMG_PATH + "Tempo Master.png";
    public static final String ABANDON_REASON = CARD_IMG_PATH + "Abandon Reason.png";
    public static final String AURA_DISCHARGE = CARD_IMG_PATH + "Aura Discharge.png";
    public static final String BREAK_THROUGH = CARD_IMG_PATH + "Break Through.png";
    public static final String CHAINER = CARD_IMG_PATH + "Chainer.png";
    public static final String DESPERATION = CARD_IMG_PATH + "Desperate Wailing.png";
    public static final String FURIOUS_SMITE = CARD_IMG_PATH + "Furious Smite.png";
    public static final String RETALIATE = CARD_IMG_PATH + "Retaliate.png";
    public static final String STRAINING_SMITE = CARD_IMG_PATH + "Chained Smite.png";
    public static final String TEAR_PSYCHE = CARD_IMG_PATH + "Tear Psyche.png";
    public static final String TEAR_SOUL = CARD_IMG_PATH + "Tear Soul.png";
    public static final String UPRISING = CARD_IMG_PATH + "Uprising.png";
    public static final String ZEALOUS_SMITE = CARD_IMG_PATH + "Zealous Smite.png";
    public static final String DELVING_PRAYER = CARD_IMG_PATH + "Delving Prayer.png";
    public static final String ENSOUL = CARD_IMG_PATH + "Ensoul.png";
    public static final String INSIGHTFUL_PRAYER = CARD_IMG_PATH + "Insightful Prayer.png";
    public static final String MIGHT_FORM = CARD_IMG_PATH + "Might Form.png";
    public static final String PUSH_ONWARD = CARD_IMG_PATH + "Push Onward.png";
    public static final String SHARPEN = CARD_IMG_PATH + "Sharpen.png";
    public static final String TURN_TABLES = CARD_IMG_PATH + "Turn Tables.png";
    public static final String ZEN = CARD_IMG_PATH + "Zen.png";
    public static final String ARCHANGEL_BOON = CARD_IMG_PATH + "Archangel's Denouement.png";
    public static final String BRACE_THE_PAIN = CARD_IMG_PATH + "Brace the pain.png";
    public static final String CHARGE = CARD_IMG_PATH + "Charge.png";
    public static final String CLEANSE = CARD_IMG_PATH + "Cleanse.png";
    public static final String COMBAT_STANCE = CARD_IMG_PATH + "Combat Dance.png";
    public static final String DUAL_SPIRIT = CARD_IMG_PATH + "Dual Spirit.png";
    public static final String GUARDIAN = CARD_IMG_PATH + "Guardian.png";
    public static final String MADDENING_REVELATION = CARD_IMG_PATH + "Maddening Revelation.png";
    public static final String MEABALIZE = CARD_IMG_PATH + "Meaballize.png";
    public static final String OVERWHELMING_LIGHT = CARD_IMG_PATH + "thought_raze.png";
    public static final String RADIANCE = CARD_IMG_PATH + "Radiance.png";
    public static final String REDUCE_TO_NOTHING = CARD_IMG_PATH + "Reduce to Nothing.png";
    public static final String SOUL_STRAIN = CARD_IMG_PATH + "Soul Strain.png";
    public static final String VINDICATION = CARD_IMG_PATH + "Vindication.png";
    public static final String ZEALOUS_AMBITION = CARD_IMG_PATH + "Zealous Ambition.png";
    public static final String BLINDING_LIGHT = CARD_IMG_PATH + "Blinding Flash.png";
    public static final String MINOR_HEALING = CARD_IMG_PATH + "Minor Healing.png";
    public static final String HOLY_LIGHT = CARD_IMG_PATH + "Holy Light.png";


    //powers
    public static final String POWER_IMG_PATH = IMG_PATH + "powers/";
    public static final String SPIRIT = POWER_IMG_PATH + "Spirit.png";
    public static final String COMBAT_STANCE_POWER =  POWER_IMG_PATH + "CombatStance.png";
    public static final String ZEN_POWER = POWER_IMG_PATH + "Zen.png";
    public static final String CHARGE_POWER = POWER_IMG_PATH + "Charge.png";
    public static final String ASCENSION_POWER = POWER_IMG_PATH + "FullAwakening.png";
    public static final String DEADLY_PLAY_POWER = POWER_IMG_PATH + "DeadlyPlay.png";
    public static final String DELVING_PRAYER_POWER = POWER_IMG_PATH + "DelvingPrayer.png";
    public static final String INSIGHTFUL_PRAYER_POWER = POWER_IMG_PATH + "InsightfulPrayer.png";
    public static final String POWER_ONWARDS_POWER_PATH = POWER_IMG_PATH + "PushOnward.png";
    public static final String MIGHT_FORM_POWER_PATH = POWER_IMG_PATH + "Might form.png";
    public static final String PEACEFUL_STANCE_POWER = POWER_IMG_PATH + "Peace.png";
    public static final String PUSH_ONWARD_POWER = POWER_IMG_PATH + "PushOnward.png";
    public static final String SHARPEN_POWER = POWER_IMG_PATH + "anvil.png";
    public static final String TEAR_SOUL_POWER = POWER_IMG_PATH + "darkness.png";
    public static final String WEAK_FORM_POWER = POWER_IMG_PATH + "weakForm.png";

    //icons
    private static final String FF9CRYSTAL_CHAR_SELECT_PATH = IMG_PATH + "charSelect/";
    private static final String FF9CRYSTAL_BUTTON = FF9CRYSTAL_CHAR_SELECT_PATH + "Logo1.png";
    private static final String FF9CRYSTAL_POTRAIT = FF9CRYSTAL_CHAR_SELECT_PATH + "Valiant_Select_Screen.png";

    // badge
    public static final String BADGE_IMG = IMG_PATH + "BaseModBadge.png";

    public static final String RELIC_IMG_PATH = IMG_PATH + "relics/";
    public static final String texturePath = RELIC_IMG_PATH + "arcanosphere.png";
    public static final String ARCHANGEL_FEATHER = RELIC_IMG_PATH + "ArchangelsFeather.png";
    public static final String BLADE_OF_LEGEND = RELIC_IMG_PATH + "BladeOfLegend.png";
    public static final String CROWN_OF_THORNS = RELIC_IMG_PATH + "CrownOfThorns.png";
    public static final String CRUMPLED_PAPER = RELIC_IMG_PATH + "CrumpledPaper.png";
    public static final String HUGE_EGG = RELIC_IMG_PATH + "HugeEgg.png";
    public static final String ORB_OF_LIGHT = RELIC_IMG_PATH + "OrbOfLight.png";
    public static final String PRAYER_BEADS = RELIC_IMG_PATH + "PrayerBeads.png";
    public static final String WEAKENING_INCENSE = RELIC_IMG_PATH + "WeakeningIncease.png";
    public static final String WINGED_NECKLACE = RELIC_IMG_PATH + "WingedNecklace.png";
    public static final String CROSS_PENDANT = RELIC_IMG_PATH + "CrossPendantPic.png";
    public static final String DIVINE_WRATH = RELIC_IMG_PATH + "Wrath.png";


    public static final Logger logger = LogManager.getLogger(FF9CrystalMod.class.getName());

    public FF9CrystalMod(){

        BaseMod.subscribe(this);

        logger.info("creating the color " + AbstractCardEnum.Crystal.toString());
        BaseMod.addColor(AbstractCardEnum.Crystal,
                CRYSTAL, CRYSTAL, CRYSTAL, CRYSTAL, CRYSTAL, CRYSTAL, CRYSTAL,
                ATTACK_WHITE, SKILL_WHITE,
                POWER_WHITE, ENERGY_ORB_CRYSTAL,
                ATTACK_WHITE_PORTRAIT, SKILL_WHITE_PORTRAIT,
                POWER_WHITE_PORTRAIT, ENERGY_ORB_CRYSTAL_PORTRAIT);

    }

    public static Texture GetSpirit_Texture()
    {
        return new Texture(SPIRIT);
    }

    public static void initialize() {
        FF9CrystalMod mod = new FF9CrystalMod();
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("Begin Character Edit");

        logger.info("add " + CharacterEnum.FF9Crystal.toString());
        BaseMod.addCharacter(new FF9Character(CardCrawlGame.playerName), FF9CRYSTAL_BUTTON,
                FF9CRYSTAL_POTRAIT, CharacterEnum.FF9Crystal);

        logger.info("Finish Character Edit");
    }
}
