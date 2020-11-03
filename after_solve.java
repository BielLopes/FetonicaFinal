/*
 * after_solve.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Nov 3 2020, 07:01 by COMSOL 5.5.0.359. */
public class after_solve {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("/home/gabriel/Documents/FotonicaFinal");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").activate("es", true);

    model.component("comp1").geom("geom1").run();

    model.param().set("R1", "1");
    model.param().set("R2", "0.5");
    model.param().set("R3", "0.3*R1");
    model.param().set("R4", "pi/4");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "R2");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R1");
    model.component("comp1").geom("geom1").feature("c3").set("r", "R3");
    model.component("comp1").geom("geom1").feature("c4").set("r", "R4");
    model.component("comp1").geom("geom1").feature("c4").set("base", "center");
    model.component("comp1").geom("geom1").feature("c4").set("pos", new int[]{1, 0});
    model.component("comp1").geom("geom1").feature("c3").set("pos", new int[]{-1, 0});
    model.component("comp1").geom("geom1").feature("c2").set("pos", new int[]{1, 1});
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{-1, 1});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{-1, 3});
    model.component("comp1").geom("geom1").feature("c2").set("pos", new int[]{1, 3});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("c2").set("pos", new int[]{1, 2});
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{-1, 2});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{10, 10});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").selection().set();

    model.param().set("epsolon1", "1");

    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"epslon1"});

    model.param().rename("epsolon1", "epslon1");

    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(1, 2, 3, 4);

    model.component("comp1").material("mat1").selection().set(1, 2, 3, 4, 5);

    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Terminated");
    model.component("comp1").physics("es").feature("term1").set("CharacteristicImpedanceFrom", "userDefined");
    model.component("comp1").physics("es").feature("term1").selection().set(5, 6, 11, 12);
    model.component("comp1").physics("es").feature("term1").set("CharacteristicImpedanceFrom", "fromPhysics");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature().duplicate("term2", "term1");
    model.component("comp1").physics("es").feature("term2").selection().set(15, 16, 19, 20);
    model.component("comp1").physics("es").feature().duplicate("term3", "term2");
    model.component("comp1").physics("es").feature("term3").selection().set(13, 14, 17, 18);
    model.component("comp1").physics("es").feature().duplicate("term4", "term3");
    model.component("comp1").physics("es").feature("term4").selection().set(7, 8, 9, 10);
    model.component("comp1").physics("es").prop("PortSweepSettings").set("useSweep", true);

    model.param().set("PortName", "1");

    model.component("comp1").physics("es").prop("PortSweepSettings").set("PortParamName", "PortName");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "R1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "R1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "PortName", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1,2,3,4", 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();
    /**
     *
     * Start frist Compile Code
     *
     */
    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("stat").set("notlistsolnum", 1);
    model.study("std1").feature("stat").set("notsolnum", "1");
    model.study("std1").feature("stat").set("listsolnum", 1);
    model.study("std1").feature("stat").set("solnum", "1");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"PortName"});
    model.batch("p1").set("plistarr", new String[]{"1,2,3,4"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("Parametric Solutions 1");

    model.batch("p1").feature("so1").set("psol", "sol2");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Electric Potential (es)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("data", "parent");

    model.batch("p1").run();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();

    /**
     *
     * End frist Compile Code
     *
     */

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
