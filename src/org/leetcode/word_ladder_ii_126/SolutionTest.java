package org.leetcode.word_ladder_ii_126;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolutionTest {

    @Test
    public void canTransormTo_1()
    {
        Solution solution = new Solution();

        assertTrue(solution.canTransormTo("hit","hot"));
    }

    @Test
    public void canTransormTo_2()
    {
        Solution solution = new Solution();

        assertFalse(solution.canTransormTo("hit","cog"));
    }

    @Test
    public void test_2()
    {
        Solution solution = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        assertTrue(solution.findLadders("hit","cog",wordList) != null);
    }

    @Test
    public void test_A2()
    {
        Solution solution = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("him");

        assertTrue(solution.findLadders("hit","dot",wordList) != null);
    }

    @Test
    public void test_5()
    {
        Solution solution = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("a");
        wordList.add("b");
        wordList.add("c");

        assertTrue(solution.findLadders("a","c",wordList) != null);
    }

    @Test
    public void test_4()
    {
        Solution solution = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dog");

        assertTrue(solution.findLadders("hot","dog",wordList) != null);
    }

    //Time limit exceeded
    @Test
    public void test_20()
    {
        Solution solution = new Solution();
        List<String> wordList = new ArrayList<>();
        wordList.add("si");
        wordList.add("go");
        wordList.add("se");
        wordList.add("cm");
        wordList.add("so");
        wordList.add("ph");
        wordList.add("mt");
        wordList.add("db");
        wordList.add("mb");
        wordList.add("sb");
        wordList.add("kr");
        wordList.add("ln");
        wordList.add("tm");
        wordList.add("le");
        wordList.add("av");
        wordList.add("sm");
        wordList.add("ar");
        wordList.add("ci");
        wordList.add("ca");
        wordList.add("br");
        wordList.add("ti");
        wordList.add("ba");
        wordList.add("to");
        wordList.add("ra");
        wordList.add("fa");
        wordList.add("yo");
        wordList.add("ow");
        wordList.add("sn");
        wordList.add("ya");
        wordList.add("cr");
        wordList.add("po");
        wordList.add("fe");
        wordList.add("ho");
        wordList.add("ma");
        wordList.add("re");
        wordList.add("or");
        wordList.add("rn");
        wordList.add("au");
        wordList.add("ur");
        wordList.add("rh");
        wordList.add("sr");
        wordList.add("tc");
        wordList.add("lt");
        wordList.add("lo");
        wordList.add("as");
        wordList.add("fr");
        wordList.add("nb");
        wordList.add("yb");
        wordList.add("if");
        wordList.add("pb");
        wordList.add("ge");
        wordList.add("th");
        wordList.add("pm");
        wordList.add("rb");
        wordList.add("sh");
        wordList.add("co");
        wordList.add("ga");
        wordList.add("li");
        wordList.add("ha");
        wordList.add("hz");
        wordList.add("no");
        wordList.add("bi");
        wordList.add("di");
        wordList.add("hi");
        wordList.add("qa");
        wordList.add("pi");
        wordList.add("os");
        wordList.add("uh");
        wordList.add("wm");
        wordList.add("an");
        wordList.add("me");
        wordList.add("mo");
        wordList.add("na");
        wordList.add("la");
        wordList.add("st");
        wordList.add("er");
        wordList.add("sc");
        wordList.add("ne");
        wordList.add("mn");
        wordList.add("mi");
        wordList.add("am");
        wordList.add("ex");
        wordList.add("pt");
        wordList.add("io");
        wordList.add("be");
        wordList.add("fm");
        wordList.add("ta");
        wordList.add("tb");
        wordList.add("ni");
        wordList.add("mr");
        wordList.add("pa");
        wordList.add("he");
        wordList.add("lr");
        wordList.add("sq");
        wordList.add("ye");


        assertTrue(solution.findLadders("qa","sq",wordList) != null);
    }

    @Test
    public void test_22() {
        Solution solution = new Solution();
        String[] array = new String[]{"kid", "tag", "pup", "ail", "tun", "woo", "erg", "luz", "brr", "gay", "sip", "kay", "per", "val", "mes", "ohs", "now", "boa", "cet", "pal", "bar", "die", "war", "hay", "eco", "pub", "lob", "rue", "fry", "lit", "rex", "jan", "cot", "bid", "ali", "pay", "col", "gum", "ger", "row", "won", "dan", "rum", "fad", "tut", "sag", "yip", "sui", "ark", "has", "zip", "fez", "own", "ump", "dis", "ads", "max", "jaw", "out", "btu", "ana", "gap", "cry", "led", "abe", "box", "ore", "pig", "fie", "toy", "fat", "cal", "lie", "noh", "sew", "ono", "tam", "flu", "mgm", "ply", "awe", "pry", "tit", "tie", "yet", "too", "tax", "jim", "san", "pan", "map", "ski", "ova", "wed", "non", "wac", "nut", "why", "bye", "lye", "oct", "old", "fin", "feb", "chi", "sap", "owl", "log", "tod", "dot", "bow", "fob", "for", "joe", "ivy", "fan", "age", "fax", "hip", "jib", "mel", "hus", "sob", "ifs", "tab", "ara", "dab", "jag", "jar", "arm", "lot", "tom", "sax", "tex", "yum", "pei", "wen", "wry", "ire", "irk", "far", "mew", "wit", "doe", "gas", "rte", "ian", "pot", "ask", "wag", "hag", "amy", "nag", "ron", "soy", "gin", "don", "tug", "fay", "vic", "boo", "nam", "ave", "buy", "sop", "but", "orb", "fen", "paw", "his", "sub", "bob", "yea", "oft", "inn", "rod", "yam", "pew", "web", "hod", "hun", "gyp", "wei", "wis", "rob", "gad", "pie", "mon", "dog", "bib", "rub", "ere", "dig", "era", "cat", "fox", "bee", "mod", "day", "apr", "vie", "nev", "jam", "pam", "new", "aye", "ani", "and", "ibm", "yap", "can", "pyx", "tar", "kin", "fog", "hum", "pip", "cup", "dye", "lyx", "jog", "nun", "par", "wan", "fey", "bus", "oak", "bad", "ats", "set", "qom", "vat", "eat", "pus", "rev", "axe", "ion", "six", "ila", "lao", "mom", "mas", "pro", "few", "opt", "poe", "art", "ash", "oar", "cap", "lop", "may", "shy", "rid", "bat", "sum", "rim", "fee", "bmw", "sky", "maj", "hue", "thy", "ava", "rap", "den", "fla", "auk", "cox", "ibo", "hey", "saw", "vim", "sec", "ltd", "you", "its", "tat", "dew", "eva", "tog", "ram", "let", "see", "zit", "maw", "nix", "ate", "gig", "rep", "owe", "ind", "hog", "eve", "sam", "zoo", "any", "dow", "cod", "bed", "vet", "ham", "sis", "hex", "via", "fir", "nod", "mao", "aug", "mum", "hoe", "bah", "hal", "keg", "hew", "zed", "tow", "gog", "ass", "dem", "who", "bet", "gos", "son", "ear", "spy", "kit", "boy", "due", "sen", "oaf", "mix", "hep", "fur", "ada", "bin", "nil", "mia", "ewe", "hit", "fix", "sad", "rib", "eye", "hop", "haw", "wax", "mid", "tad", "ken", "wad", "rye", "pap", "bog", "gut", "ito", "woe", "our", "ado", "sin", "mad", "ray", "hon", "roy", "dip", "hen", "iva", "lug", "asp", "hui", "yak", "bay", "poi", "yep", "bun", "try", "lad", "elm", "nat", "wyo", "gym", "dug", "toe", "dee", "wig", "sly", "rip", "geo", "cog", "pas", "zen", "odd", "nan", "lay", "pod", "fit", "hem", "joy", "bum", "rio", "yon", "dec", "leg", "put", "sue", "dim", "pet", "yaw", "nub", "bit", "bur", "sid", "sun", "oil", "red", "doc", "moe", "caw", "eel", "dix", "cub", "end", "gem", "off", "yew", "hug", "pop", "tub", "sgt", "lid", "pun", "ton", "sol", "din", "yup", "jab", "pea", "bug", "gag", "mil", "jig", "hub", "low", "did", "tin", "get", "gte", "sox", "lei", "mig", "fig", "lon", "use", "ban", "flo", "nov", "jut", "bag", "mir", "sty", "lap", "two", "ins", "con", "ant", "net", "tux", "ode", "stu", "mug", "cad", "nap", "gun", "fop", "tot", "sow", "sal", "sic", "ted", "wot", "del", "imp", "cob", "way", "ann", "tan", "mci", "job", "wet", "ism", "err", "him", "all", "pad", "hah", "hie", "aim", "ike", "jed", "ego", "mac", "baa", "min", "com", "ill", "was", "cab", "ago", "ina", "big", "ilk", "gal", "tap", "duh", "ola", "ran", "lab", "top", "gob", "hot", "ora", "tia", "kip", "han", "met", "hut", "she", "sac", "fed", "goo", "tee", "ell", "not", "act", "gil", "rut", "ala", "ape", "rig", "cid", "god", "duo", "lin", "aid", "gel", "awl", "lag", "elf", "liz", "ref", "aha", "fib", "oho", "tho", "her", "nor", "ace", "adz", "fun", "ned", "coo", "win", "tao", "coy", "van", "man", "pit", "guy", "foe", "hid", "mai", "sup", "jay", "hob", "mow", "jot", "are", "pol", "arc", "lax", "aft", "alb", "len", "air", "pug", "pox", "vow", "got", "meg", "zoe", "amp", "ale", "bud", "gee", "pin", "dun", "pat", "ten", "mob"};

        assertTrue(solution.findLadders("cet", "ism", Arrays.asList(array)) != null);
    }


}
