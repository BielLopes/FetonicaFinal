/*
 * test_pro_lspr.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

/** Model exported on Nov 4 2020, 03:47 by COMSOL 5.5.0.359. */
public class test_pro_lspr {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("/home/gabriel/Documents/FetonicaFinal");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("ngen", "5");
    model.study("std1").feature("wave").activate("ewfd", true);

    model.param().set("na", "1.33");
    model.param().descr("na", "Refractive index, air");
    model.param().set("nb", "1.58");
    model.param().descr("nb", "Refractive index, dielectric");
    model.param().set("d", "200[nm]");
    model.param().descr("d", "Grating constant");
    model.param().set("lb", "600[nm]");
    model.param().descr("lb", "Vacuum wavelength");
    model.param().set("f0", "c_const/lb");
    model.param().descr("f0", "Frequency");
    model.param().set("alpha", "0");
    model.param().descr("alpha", "Angle of incidence");
    model.param().set("beta", "asin(na*sin(alpha)/nb)");
    model.param().descr("beta", "Refraction angle");
    model.param().set("gap", "20.0[nm]");
    model.param().descr("gap", "");
    model.param().set("gold_surf_wd", "50[nm]");
    model.param().descr("gold_surf_wd", "");
    model.param().set("gold_surf_hg", "10[nm]");
    model.param().descr("gold_surf_hg", "");
    model.param().set("surface", "10[nm]");
    model.param().descr("surface", "");
    model.param().set("pedestal", "20[nm]");
    model.param().descr("pedestal", "");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("Top");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d+gap+gold_surf_wd", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "(d*3)+gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-(d+gap+gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("Down");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"d+gap+gold_surf_wd", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "(d*3)+gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-(d+gap+gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "-((d*3)+gold_surf_hg)", 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("gold_top");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"gold_surf_wd", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-(gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "surface+pedestal", 1);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("surface");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"gold_surf_wd", "1"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "surface", 1);
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-(gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "pedestal", 1);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").label("pedestal");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"pedestal/2", "pedestal"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"-pedestal/4", "0"});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").label("mesh");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"gold_surf_wd*1.5", "1"});
    model.component("comp1").geom("geom1").feature("r6").setIndex("size", "surface+pedestal+2*gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"-(gold_surf_wd*1.5)/2", "0"});
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").run("fin");

    /**
    
      Até aqui foram definidos os parametros e criadas as geometrias

    */

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("surrounding");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"na"});
    model.component("comp1").material("mat1").selection().set(1, 2, 3);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Dieletric");
    model.component("comp1").material("mat2").selection().set(4, 6);
    model.component("comp1").material("mat2").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nb"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").label("Au - Gold");
    model.component("comp1").material("mat3").set("family", "gold");
    model.component("comp1").material("mat3").set("groups", new String[][]{{"metals", "Metals"}});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"45.6e6[S/m]", "0", "0", "0", "45.6e6[S/m]", "0", "0", "0", "45.6e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").descr("electricconductivity_symmetry", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"14.2e-6[1/K]", "0", "0", "0", "14.2e-6[1/K]", "0", "0", "0", "14.2e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").descr("thermalexpansioncoefficient_symmetry", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "129[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("heatcapacity_symmetry", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "19300[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("density_symmetry", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"317[W/(m*K)]", "0", "0", "0", "317[W/(m*K)]", "0", "0", "0", "317[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").descr("thermalconductivity_symmetry", "");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("youngsmodulus", "70e9[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").descr("youngsmodulus_symmetry", "");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("poissonsratio", "0.44");
    model.component("comp1").material("mat3").propertyGroup("Enu").descr("poissonsratio_symmetry", "");
    model.component("comp1").material("mat3").set("groups", new String[][]{});
    model.component("comp1").material("mat3").set("family", "gold");
    model.component("comp1").material("mat3").selection().set(5);
    model.component("comp1").material("mat3").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", new String[]{"0.18104"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("ki", new String[]{"3.068099"});

    /**

    Foi definidos os materiais a serem utilizados na simulação

    */

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(24, 25);

    /**
    
      Foi criado uma slection expliciti

    */

    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(5);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port1").set("InputType", "H");
    model.component("comp1").physics("ewfd").feature("port1").set("Hampl", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("port1").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("port1")
         .set("n", new String[]{"na", "0", "0", "0", "na", "0", "0", "0", "na"});
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(2);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port2").set("InputType", "H");
    model.component("comp1").physics("ewfd").feature("port2").set("Hampl", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("port2")
         .set("n", new String[]{"nb", "0", "0", "0", "nb", "0", "0", "0", "nb"});
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 3, 24, 25);
    model.component("comp1").physics("ewfd").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc1").set("Floquet_source", "FromPeriodicPort");

    /**
    
      Foram criadas as duas portas periodicas (superior e inferior) e acondição de peridiciosidade

    */

    model.component("comp1").mesh("mesh1").contribute("physics/ewfd", false);
    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();

    /**
    
      Foi definida uma malha extra fina para o modelo

    */

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "na", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "na", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "lb", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(400[nm],1[nm],900[nm])", 0);
    model.study("std1").feature("wave").set("plist", "lb");

    /**
    
      Criado um parametric Sweep e o wavelength ficou sendo controlado pelo 'lb'

    */

    model.sol().create("sol1");
    model.sol("sol1").study("std1");

    model.study("std1").feature("wave").set("notlistsolnum", 1);
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("listsolnum", 1);
    model.study("std1").feature("wave").set("solnum", "auto");

    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "wave");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "wave");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.01);
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("pname", new String[]{"lambda0"});
    model.sol("sol1").feature("s1").feature("p1").set("plistarr", new String[]{"lb"});
    model.sol("sol1").feature("s1").feature("p1").set("punit", new String[]{"\u00b5m"});
    model.sol("sol1").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol1").feature("s1").feature("p1").set("preusesol", "auto");
    model.sol("sol1").feature("s1").feature("p1").set("pdistrib", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plot", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plotgroup", "Default");
    model.sol("sol1").feature("s1").feature("p1").set("probesel", "all");
    model.sol("sol1").feature("s1").feature("p1").set("probes", new String[]{});
    model.sol("sol1").feature("s1").feature("p1").set("control", "wave");
    model.sol("sol1").feature("s1").set("control", "wave");
    model.sol("sol1").feature("s1").feature("aDef").set("complexfun", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s1").feature("d1").label("Suggested Direct Solver (ewfd)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"lb"});
    model.batch("p1").set("plistarr", new String[]{"range(400[nm],1[nm],900[nm])"});
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
    model.result("pg1").label("Electric Field (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Torder_0", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"Reflectance, order 0", "Transmittance, order 0", "Total reflectance and transmittance", "Absorptance"});
    model.result("pg2").label("Reflectance, Transmittance, and Absorptance (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Reflectance, transmittance, and absorptance (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "lb");
    model.result("pg2").feature("glob1").set("xdataunit", "m");
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("data", "dset2");
    model.result().dataset("grid1").set("par1", "PHASE");
    model.result().dataset("grid1").set("parmax1", "2*pi/1.0");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("Polarization Plot (ewfd)");
    model.result("pg3").set("data", "grid1");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "Jones vector, out-of-plane component");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "Jones vector, in-plane component");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "Polarization states, Color: Phase (Radians)");
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", -0.5);
    model.result("pg3").set("xmax", 0.5);
    model.result("pg3").set("ymin", -0.5);
    model.result("pg3").set("ymax", 0.5);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1")
         .set("expr", "if(root.comp1.ewfd.normJR_0==0,0,0.5*root.comp1.ewfd.JRIP_0*exp(j*PHASE)/root.comp1.ewfd.normJR_0)+(0)");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1")
         .set("xdataexpr", "if(root.comp1.ewfd.normJR_0==0,0,0.5*root.comp1.ewfd.JROOP_0*exp(j*PHASE)/root.comp1.ewfd.normJR_0)+(0)");
    model.result("pg3").feature("lngr1").set("linestyle", "solid");
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature("lngr1").set("smooth", "none");
    model.result("pg3").feature("lngr1").create("col1", "Color");
    model.result("pg3").feature("lngr1").feature("col1").set("expr", "PHASE");
    model.result("pg3").feature("lngr1").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("lngr1").feature("col1").set("colorlegend", true);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "Reflection", 0);
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("text", "m=0");
    model.result("pg3").feature("ann1").set("posexpr", new String[]{"0", "0"});
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2")
         .set("expr", "if(root.comp1.ewfd.normJT_0==0,0,0.5*root.comp1.ewfd.JTIP_0*exp(j*PHASE)/root.comp1.ewfd.normJT_0)+(0)");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2")
         .set("xdataexpr", "if(root.comp1.ewfd.normJT_0==0,0,0.5*root.comp1.ewfd.JTOOP_0*exp(j*PHASE)/root.comp1.ewfd.normJT_0)+(0)");
    model.result("pg3").feature("lngr2").set("linestyle", "dashed");
    model.result("pg3").feature("lngr2").set("linewidth", 2);
    model.result("pg3").feature("lngr2").set("smooth", "none");
    model.result("pg3").feature("lngr2").create("col1", "Color");
    model.result("pg3").feature("lngr2").feature("col1").set("expr", "PHASE");
    model.result("pg3").feature("lngr2").feature("col1").set("colortable", "Cyclic");
    model.result("pg3").feature("lngr2").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "Transmission", 0);

    model.batch("p1").run();

    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg3").setIndex("looplevelinput", "manual", 1);
    model.result("pg3").setIndex("looplevel", new int[]{1}, 1);

    /**
  
    Todas essas últimas 150 linhas são resultado  de computar o modelo  pela primeira vez

    */

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("expr", "1/(abs(ewfd.S21)^2)", 0);
    model.result().numerical("gev1").set("tablecols", "inner");

    /**
    
      Definido o global evaluation

    */

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Global Evaluation 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "none");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("source", "table");
    model.result("pg4").feature("tblp1").set("table", "tbl1");
    model.result("pg4").run();

    /**
    
      Mandei Rodar o global evaluation em uma nova tabela pela primeira vez

    */

    model.param().set("gap", "23.0[nm]");
    model.param().set("gold_surf_wd", "54[nm]");
    model.param().set("gold_surf_hg", "8[nm]");
    model.param().set("surface", "12[nm]");
    model.param().set("pedestal", "18[nm]");

    model.component("comp1").geom("geom1").run("fin");

    /**
    
      Troquei os valores dos parametros para obter novas soluções e recomputei as geometrias

    */

    model.sol("sol1").study("std1");

    model.study("std1").feature("wave").set("notlistsolnum", 1);
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("listsolnum", 1);
    model.study("std1").feature("wave").set("solnum", "auto");

    model.sol("sol1").feature().remove("s1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "wave");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "wave");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.01);
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("pname", new String[]{"lambda0"});
    model.sol("sol1").feature("s1").feature("p1").set("plistarr", new String[]{"lb"});
    model.sol("sol1").feature("s1").feature("p1").set("punit", new String[]{"\u00b5m"});
    model.sol("sol1").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol1").feature("s1").feature("p1").set("preusesol", "auto");
    model.sol("sol1").feature("s1").feature("p1").set("pdistrib", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plot", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plotgroup", "pg1");
    model.sol("sol1").feature("s1").feature("p1").set("probesel", "all");
    model.sol("sol1").feature("s1").feature("p1").set("probes", new String[]{});
    model.sol("sol1").feature("s1").feature("p1").set("control", "wave");
    model.sol("sol1").feature("s1").set("control", "wave");
    model.sol("sol1").feature("s1").feature("aDef").set("complexfun", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s1").feature("d1").label("Suggested Direct Solver (ewfd)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.batch("p1").feature().remove("so1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").set("pname", new String[]{"lb"});
    model.batch("p1").set("plistarr", new String[]{"range(400[nm],1[nm],900[nm])"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");
    model.batch("p1").run();

    model.result("pg1").run();

    /**
    
      Os últimos 55 comandos são causados ao computar novamente o modelo

    */


    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    /**
    
      Atualizando a table 1

    */


    return model;
  }

  /**
    Para esse teste, o objetivo é testar capturar os dados das matrizes, mudar os parametros e recapturar o novo dado!
  */

  public static void changeParams(Model model, int[] params){
    model.param().set("gap", Integer.toString(params[0]) + "[nm]");
    model.param().set("gold_surf_wd", Integer.toString(params[1]) + "[nm]");
    model.param().set("gold_surf_hg", Integer.toString(params[2]) + "[nm]");
    model.param().set("surface", Integer.toString(params[3]) + "[nm]");
    model.param().set("pedestal", Integer.toString(params[4]) + "[nm]");

    model.component("comp1").geom("geom1").run("fin");
  }

  public static void evaluateModel(Model model){

    model.sol("sol1").study("std1");

    model.study("std1").feature("wave").set("notlistsolnum", 1);
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("listsolnum", 1);
    model.study("std1").feature("wave").set("solnum", "auto");

    model.sol("sol1").feature().remove("s1");
    model.sol("sol1").feature().remove("v1");
    model.sol("sol1").feature().remove("st1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "wave");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "wave");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 0.01);
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("pname", new String[]{"lambda0"});
    model.sol("sol1").feature("s1").feature("p1").set("plistarr", new String[]{"lb"});
    model.sol("sol1").feature("s1").feature("p1").set("punit", new String[]{"\u00b5m"});
    model.sol("sol1").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol1").feature("s1").feature("p1").set("preusesol", "auto");
    model.sol("sol1").feature("s1").feature("p1").set("pdistrib", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plot", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plotgroup", "pg1");
    model.sol("sol1").feature("s1").feature("p1").set("probesel", "all");
    model.sol("sol1").feature("s1").feature("p1").set("probes", new String[]{});
    model.sol("sol1").feature("s1").feature("p1").set("control", "wave");
    model.sol("sol1").feature("s1").set("control", "wave");
    model.sol("sol1").feature("s1").feature("aDef").set("complexfun", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s1").feature("d1").label("Suggested Direct Solver (ewfd)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.batch("p1").feature().remove("so1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol1");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").set("pname", new String[]{"lb"});
    model.batch("p1").set("plistarr", new String[]{"range(400[nm],1[nm],900[nm])"});
    model.batch("p1").set("sweeptype", "sparse");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");
    model.batch("p1").run();

    model.result("pg1").run();


    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    int value = count();
    createLog("Consegui em tese rodar um array aleatório de número: " + value, "computed");
  }

  public static int count(){

    int counter = -1;

    try{
      FileReader fr = new FileReader("count.txt");
      BufferedReader br = new BufferedReader(fr);

      String str;
      str = br.readLine();
      counter = Integer.parseInt(str);
      counter++;
      br.close();

      FileWriter fw = new FileWriter("count.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.println(Integer.toString(counter));
      pw.close();
    }catch(IOException e){
      System.out.println("Faking Error");
    }

    return counter;

  }

  public static int get_count(){

    int counter = 0;

    try{
      FileReader fr = new FileReader("count.txt");
      BufferedReader br = new BufferedReader(fr);

      String str;
      str = br.readLine();
      counter = Integer.parseInt(str);
      br.close();
    }catch(IOException e){
      System.out.println("Faking Error");
    }

    return counter;

  }

  public static void createLog(String info, String type){

    Random generator = new Random();
    int logNumber = generator.nextInt(999999999);
    try{
      String label = "Custom_log_file_" + type + "_" + Integer.toString(logNumber) + ".txt";
      FileWriter fw = new FileWriter(label);
      PrintWriter pw = new PrintWriter(fw);
      pw.println(info);
      pw.close();

    }catch(IOException e){
      System.out.println("Faking Error");
    }

    System.out.println("Log creeated");

  }

  public static void saveNumber(double number, String label){

    try{
      FileWriter fw = new FileWriter(label);
      PrintWriter pw = new PrintWriter(fw);
      pw.println(Double.toString(number));
      pw.close();

    }catch(IOException e){
      System.out.println("Faking Error");
    }

    System.out.println(Double.toString(number));
  }

  public static double[] catchDataTable(Model model){

    int rows = 501;
    int columns = 3 + get_count();

    String[][] ArrayTemp = new String[rows][columns];
    double[][] table = new double[columns][rows];

    ArrayTemp = model.result().table("tbl1").getTableData(false);

    for (int i = 0; i < rows; i++){
      for (int j = 0; j < columns; j++){
        //saveNumber(Double.parseDouble(ArrayTemp[i][j]), "myDATA.txt");
        table[j][i] = Double.parseDouble(ArrayTemp[i][j]);
      }
    }

    createLog("Parece que deu certo dar load na tabela", "sucsess");

    return table[columns-1].clone();
  }

  public static double handleLastMaxValue(Model model){
    double[] values = catchDataTable(model);
    double max = Arrays.stream(values).max().getAsDouble();
    return max;
  }

  public static int[][] permutations(int[] crm1, int[] crm2){
    Random generator = new Random();
    int[][] nextGeneration = new int[10][5];

    for (int i = 0; i < 8; i++){
      int slicer = 2;
      double luck = generator.nextDouble();
      if (luck > 0.5){
        slicer = 3;
      }
      for (int j = 0; j < 5; j++){
        if (j < slicer){
          nextGeneration[i][j] = crm1[j];
        }else {
          nextGeneration[i][j] = crm2[j];
        }
      }
      luck = generator.nextDouble();
      boolean mutated = false;
      if (luck > 0.3) mutated = true;
      if (mutated){
        int position = generator.nextInt(5);
        int newValue;
        switch (position){
          case 0:
            newValue = generator.nextInt(11) + 15;
            nextGeneration[i][position] = newValue;
            break;
          case 1:
            newValue = generator.nextInt(51) + 25;
            nextGeneration[i][position] = newValue;
            break;
          case 2:
            newValue = generator.nextInt(21) + 5;
            nextGeneration[i][position] = newValue;
            break;
          case 3:
            newValue = generator.nextInt(21) + 5;
            nextGeneration[i][position] = newValue;
            break;
          case 4:
            newValue = generator.nextInt(46) + 5;
            nextGeneration[i][position] = newValue;
            break;
        }
      }


    }

    for (int i = 0; i < 5; i++ ){
      nextGeneration[8][i] = crm1[i];
      nextGeneration[9][i] = crm2[i];
    }

    return  nextGeneration.clone();
  }

  public static int[] randomInit(){
    int[] initial = new int[5];
    Random generator = new Random();

    initial[0] = generator.nextInt(11) + 15;
    initial[1] = generator.nextInt(51) + 25;
    initial[2] = generator.nextInt(21) + 5;
    initial[3] = generator.nextInt(21) + 5;
    initial[4] = generator.nextInt(46) + 5;

    return initial.clone();
  }

  public static void geneticAlg(Model model){
    int[] is1 = randomInit();
    int[] is2 = randomInit();

    int[][] nextGeneration = permutations(is1, is2);
    double[] maxValues = new double[10];

    while(true){
      for(int i = 0; i < 10; i++){
        changeParams(model, nextGeneration[i]);
        evaluateModel(model);

        double newMaxValue = handleLastMaxValue(model);
        maxValues[i] = newMaxValue;
      }

      for(int i = 0; i < 10; i++){
        String label = "Last_population_" + i + ".txt";
        saveNumber(maxValues[i], label);
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("comessado uma longa jornada");
    int tst = 55;
    Model model = run();
    //catchDataTable(model);
    int[] cobaia = randomInit();
    createLog("O valor de gap: " + cobaia[0], "info");
    createLog("O valor de gold_surf_wd: " + cobaia[1], "info");
    createLog("O valor de gold_surf_hg: " + cobaia[2], "info");
    createLog("O valor de surface: " + cobaia[3], "info");
    createLog("O valor de pedestal: " + cobaia[4], "info");
    changeParams(model, cobaia);
    evaluateModel(model);
    double newMaxValue = handleLastMaxValue(model);
    createLog("O fluxo principal funcionou!!!!", "evaluate");
    System.out.println(newMaxValue);
    System.out.println(tst);
  }

}
