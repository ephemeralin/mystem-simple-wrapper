package com.ephemeralin.mystemsimple;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Grammeme type.
 */
@Getter
public enum GramType {
    ADJECTIVE("A"),          // прилагательное
    ADVERB("ADV"),        // наречие
    ADVERB_PRONOMINAL("ADVPRO"),     // местоименное наречие
    ADJECTIVE_NUMERAL("ANUM"),       // числительное-прилагательное
    ADJECTIVE_PRONOMINAL("APRO"),       // местоимение-прилагательное
    COMPOSITE("COM"),        // часть композита - сложного слова
    CONJUNCTION("CONJ"),       // союз
    INTERJECTION("INTJ"),       // междометие
    NUMERAL("NUM"),        // числительное
    PARTICLE("PART"),       // частица
    PREPOSITION("PR"),         // предлог
    NOUN("S"),          // существительное
    NOUN_PRONOMINAL("SPRO"),       // местоимение-существительное
    VERB("V"),          // глагол

    praes("praes"),      // настоящее
    inpraes("inpraes"),    // непрошедшее
    praet("praet"),      // прошедшее

    nom("nom"),        // именительный
    gen("gen"),        // родительный
    dat("dat"),        // дательный
    acc("acc"),        // винительный
    ins("ins"),        // творительный
    abl("abl"),        // предложный
    part("part"),       // партитив (второй родительный)
    loc("loc"),        // местный (второй предложный)
    voc("voc"),        // звательный

    sg("sg"),         // единственное число
    pl("pl"),         // множественное число

    ger("ger"),        // деепричастие
    inf("inf"),        // инфинитив
    partcp("partcp"),     // причастие
    indic("indic"),      // изьявительное наклонение
    imper("imper"),      // повелительное наклонение

    brev("brev"),       // краткая форма
    plen("plen"),       // полная форма
    poss("poss"),       // притяжательные прилагательные

    supr("supr"),       // превосходная
    comp("comp"),       // сравнительная

    _1p("_1p"),        // 1-е лицо
    _2p("_2p"),        // 2-е лицо
    _3p("_3p"),        // 3-е лицо

    m("m"),          // мужской род
    f("f"),          // женский род
    n("n"),          // средний род

    ipf("ipf"),        // несовершенный
    pf("pf"),         // совершенный

    act("act"),        // действительный залог
    pass("pass"),       // страдательный залог

    anim("anim"),       // одушевленное
    inan("inan"),       // неодушевленное

    tran("tran"),       // переходный глагол
    intr("intr"),       // непереходный глагол

    parenth("parenth"),    // вводное слово
    geo("geo"),        // географическое название
    awkw("awkw"),       // образование формы затруднено
    persn("persn"),      // имя собственное
    dist("dist"),       // искаженная форма
    mf("mf"),         // общая форма мужского и женского рода
    obsc("obsc"),       // обсценная лексика
    patrn("patrn"),      // отчество
    praed("praed"),      // предикатив
    inform("inform"),     // разговорная форма
    rare("rare"),       // редко встречающееся слово
    abbr("abbr"),       // сокращение
    obsol("obsol"),      // устаревшая форма
    famn("famn");       // фамилия

    private String type;

    public static final Map<String, GramType> COLLECTED =
            Arrays.asList(GramType.values()).stream()
                    .collect(Collectors
                            .toMap(GramType::getType, a -> a));

    GramType(String type) {
        this.type = type;
    }
}
