/*
 * lspr.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

/** Model exported on Nov 3 2020, 02:31 by COMSOL 5.5.0.359. */
public class lspr {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("/home/gabriel/Documents/FotonicaFinal");

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
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d+gap+gold_surf_wd", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "(d*3)+gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-(d+gap+gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature("r1").label("Top");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").label("Down");
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r1");
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature().duplicate("r5", "r4");
    model.component("comp1").geom("geom1").feature().duplicate("r6", "r5");
    model.component("comp1").geom("geom1").feature("r3").label("gold_top");
    model.component("comp1").geom("geom1").feature("r4").label("surface");
    model.component("comp1").geom("geom1").feature("r5").label("pedestal");
    model.component("comp1").geom("geom1").feature("r6").label("mesh");
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "(d*3)+gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "-((d*3)+gold_surf_hg)", 1);
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "gold_surf_wd", 0);
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-(gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "surface+pedestal", 1);
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "gold_surf_wd", 0);
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "surfaces", 1);
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"-(gold_surf_wd)/2", "0"});
    model.component("comp1").geom("geom1").feature("r4").setIndex("pos", "pedestal", 1);
    model.component("comp1").geom("geom1").feature("r4").setIndex("size", "surface", 1);
    model.component("comp1").geom("geom1").feature("r5").setIndex("size", "pedestal/2", 0);
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"pedestal/2", "pedestal"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"-pedestal/4", "0"});
    model.component("comp1").geom("geom1").feature("r6").setIndex("size", "gold_surf_wd*1.5", 0);
    model.component("comp1").geom("geom1").feature("r6").setIndex("size", "surface+pedestal+2*gold_surf_hg", 1);
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"-(gold_surf_wd*1.5)/2", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port1").set("InputType", "H");
    model.component("comp1").physics("ewfd").feature("port1").set("Hampl", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("port1").selection().set(5);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port2").set("InputType", "H");
    model.component("comp1").physics("ewfd").feature("port2").set("Hampl", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("port2").selection().set(2);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 3, 24, 25);
    model.component("comp1").physics("ewfd").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc1").set("Floquet_source", "FromPeriodicPort");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").set("sys", "none");
    model.component("comp1").material("mat1").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"na", "0", "0", "0", "na", "0", "0", "0", "na"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").info("category").title(null);
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").info("category")
         .body("Electromagnetic_models");
    model.component("comp1").material("mat1").set("sys", "none");
    model.component("comp1").material("mat1").selection().set(1, 2, 3);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").set("sys", "none");
    model.component("comp1").material("mat2").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nb", "0", "0", "0", "nb", "0", "0", "0", "nb"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").info("category").title(null);
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").info("category")
         .body("Electromagnetic_models");
    model.component("comp1").material("mat2").set("sys", "none");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").set("sys", "none");
    model.component("comp1").material("mat3").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("n", "");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").set("ki", "");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"n_interp(1[1/m]*c_const/freq)", "0", "0", "0", "n_interp(1[1/m]*c_const/freq)", "0", "0", "0", "n_interp(1[1/m]*c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"k_interp(1[1/m]*c_const/freq)", "0", "0", "0", "k_interp(1[1/m]*c_const/freq)", "0", "0", "0", "k_interp(1[1/m]*c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("n_interp", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("n_interp")
         .set("table", new String[][]{{"2.0659999999999998E-7", "1.25223320824"},
         {"2.1089508319E-7", "1.28860501667"},
         {"2.15279458439E-7", "1.32053986347"},
         {"2.19754982075E-7", "1.34888336249"},
         {"2.24323549012E-7", "1.3746284289"},
         {"2.28987093564E-7", "1.39882611415"},
         {"2.33747590255E-7", "1.42248550103"},
         {"2.38607054657E-7", "1.44647562039"},
         {"2.43567544248E-7", "1.47144312879"},
         {"2.4863115927599996E-7", "1.49775668894"},
         {"2.5380004365499994E-7", "1.52548341112"},
         {"2.59076385867E-7", "1.55439629116"},
         {"2.64462419893E-7", "1.58400639322"},
         {"2.69960426156E-7", "1.6136108117"},
         {"2.75572732488E-7", "1.64234733089"},
         {"2.81301715116E-7", "1.66924858331"},
         {"2.87149799665E-7", "1.69329166131"},
         {"2.9311946218899997E-7", "1.71344305996"},
         {"2.99213230217E-7", "1.72870316606"},
         {"3.0543368382399997E-7", "1.73815864557"},
         {"3.11783456723E-7", "1.74105369497"},
         {"3.1826523738099996E-7", "1.73689001004"},
         {"3.2488177015399996E-7", "1.72555779493"},
         {"3.31635856454E-7", "1.70748407952"},
         {"3.3853035593199995E-7", "1.68376050988"},
         {"3.45568187689E-7", "1.65618696109"},
         {"3.5275233151199993E-7", "1.62715563874"},
         {"3.60085829136E-7", "1.5993263363"},
         {"3.67571785533E-7", "1.57512081975"},
         {"3.75213370223E-7", "1.55616625486"},
         {"3.8301381862099995E-7", "1.5428732988"},
         {"3.90976433402E-7", "1.53428802988"},
         {"3.99104585903E-7", "1.5282356392"},
         {"4.0740171754799997E-7", "1.52166602777"},
         {"4.15871341306E-7", "1.51107621084"},
         {"4.2451704317999997E-7", "1.49291226063"},
         {"4.33342483722E-7", "1.46390381788"},
         {"4.42351399584E-7", "1.42132874128"},
         {"4.5154760510399996E-7", "1.36323722341"},
         {"4.60934993913E-7", "1.28868530644"},
         {"4.70517540592E-7", "1.19803128159"},
         {"4.80299302349E-7", "1.0933056197"},
         {"4.9028442074E-7", "0.97852316585"},
         {"5.0047712342E-7", "0.859575063526"},
         {"5.10881725935E-7", "0.743288882063"},
         {"5.215026335489999E-7", "0.635790656008"},
         {"5.32344343107E-7", "0.541093350059"},
         {"5.434114449429999E-7", "0.460739155424"},
         {"5.547086248179999E-7", "0.394373918234"},
         {"5.6624066591E-7", "0.340593024822"},
         {"5.78012450835E-7", "0.297605602816"},
         {"5.90028963715E-7", "0.263621057179"},
         {"6.02295292289E-7", "0.237028009053"},
         {"6.14816630068E-7", "0.21645383529"},
         {"6.275982785319999E-7", "0.200765474753"},
         {"6.40645649374E-7", "0.189045341648"},
         {"6.539642667949999E-7", "0.180559280841"},
         {"6.675597698389999E-7", "0.174724390804"},
         {"6.814379147839999E-7", "0.171079979374"},
         {"6.95604577575E-7", "0.169262728402"},
         {"7.10065756315E-7", "0.168986132123"},
         {"7.24827573806E-7", "0.170023837191"},
         {"7.39896280135E-7", "0.172196353782"},
         {"7.552782553289999E-7", "0.175360582261"},
         {"7.709800120469999E-7", "0.179401637013"},
         {"7.87008198346E-7", "0.184226512035"},
         {"8.033696004889999E-7", "0.189759203016"},
         {"8.200711458239999E-7", "0.195936968685"},
         {"8.37119905712E-7", "0.202707475388"},
         {"8.545230985229999E-7", "0.2100266216"},
         {"8.72288092693E-7", "0.217856882985"},
         {"8.904224098449999E-7", "0.22616605438"},
         {"9.08933727968E-7", "0.234926293578"},
         {"9.278298846739999E-7", "0.244113394196"},
         {"9.471188805139999E-7", "0.253706232338"},
         {"9.668088823649999E-7", "0.263686345098"},
         {"9.86908226887E-7", "0.274037609153"},
         {"1.00742542406E-6", "0.284745995453"},
         {"1.02836916076E-6", "0.295799381814"},
         {"1.0497483045E-6", "0.307187409657"},
         {"1.07157190709E-6", "0.318901374431"},
         {"1.09384920857E-6", "0.330934141782"},
         {"1.11658964103E-6", "0.343280083388"},
         {"1.13980283269E-6", "0.355935027838"},
         {"1.1634986119099998E-6", "0.368896223022"},
         {"1.18768701137E-6", "0.382162307315"},
         {"1.21237827235E-6", "0.395733287491"},
         {"1.237582849E-6", "0.409610521811"},
         {"1.26331141285E-6", "0.42379670708"},
         {"1.28957485725E-6", "0.438295868801"},
         {"1.3163843020299998E-6", "0.453113353762"},
         {"1.34375109819E-6", "0.468255824603"},
         {"1.37168683271E-6", "0.48373125601"},
         {"1.4002033334699998E-6", "0.499548932349"},
         {"1.42931267422E-6", "0.515719446594"},
         {"1.45902717974E-6", "0.532254700497"},
         {"1.4893594310099999E-6", "0.549167905985"},
         {"1.52032227059E-6", "0.566473587824"},
         {"1.55192880799E-6", "0.584187587597"},
         {"1.58419242529E-6", "0.60232706909"},
         {"1.6171267827699999E-6", "0.620910525167"},
         {"1.65074582469E-6", "0.639957786243"},
         {"1.6850637852E-6", "0.659490030471"},
         {"1.72009519439E-6", "0.679529795737"},
         {"1.7558548843899998E-6", "0.700100993588"},
         {"1.7923579956999999E-6", "0.721228925198"},
         {"1.82961998359E-6", "0.742940299461"},
         {"1.8676566246099998E-6", "0.765263253341"},
         {"1.90648402331E-6", "0.788227374534"},
         {"1.9461186190499997E-6", "0.81186372657"},
         {"1.98657719294E-6", "0.836204876404"},
         {"2.02787687497E-6", "0.861284924597"},
         {"2.0700351512299997E-6", "0.887139538148"},
         {"2.11306987137E-6", "0.913805986037"},
         {"2.15699925609E-6", "0.941323177553"},
         {"2.2018419049000002E-6", "0.969731703453"},
         {"2.24761680399E-6", "0.999073880003"},
         {"2.29434333425E-6", "1.02939379595"},
         {"2.3420412794899998E-6", "1.06073736249"},
         {"2.39073083481E-6", "1.09315236616"},
         {"2.44043261516E-6", "1.12668852494"},
         {"2.4911676640499995E-6", "1.16139754724"},
         {"2.5429574624799997E-6", "1.19733319414"},
         {"2.59582393804E-6", "1.23455134469"},
         {"2.64978947414E-6", "1.27311006436"},
         {"2.7048769195800003E-6", "1.31306967671"},
         {"2.76110959812E-6", "1.3544928382"},
         {"2.81851131845E-6", "1.3974446162"},
         {"2.8771063842E-6", "1.44199257024"},
         {"2.9369196042699995E-6", "1.48820683645"},
         {"2.99797630331E-6", "1.53616021518"},
         {"3.06030233246E-6", "1.58592826192"},
         {"3.1239240803E-6", "1.63758938132"},
         {"3.1888684839999997E-6", "1.69122492445"},
         {"3.2551630407199996E-6", "1.7469192892"},
         {"3.32283581931E-6", "1.80476002384"},
         {"3.39191547211E-6", "1.86483793364"},
         {"3.46243124716E-6", "1.92724719063"},
         {"3.5344130005299997E-6", "1.99208544622"},
         {"3.6078912089699998E-6", "2.05945394691"},
         {"3.68289698284E-6", "2.12945765279"},
         {"3.7594620792599997E-6", "2.20220535878"},
         {"3.837618915559999E-6", "2.27780981863"},
         {"3.91740058299E-6", "2.35638787144"},
         {"3.99884086078E-6", "2.43806057054"},
         {"4.08197423038E-6", "2.52295331476"},
         {"4.1668358901E-6", "2.6111959817"},
         {"4.25346176999E-6", "2.70292306299"},
         {"4.34188854708E-6", "2.79827380114"},
         {"4.43215366088E-6", "2.89739232795"},
         {"4.52429532923E-6", "3.00042780404"},
         {"4.6183525645400004E-6", "3.10753455933"},
         {"4.714365190209999E-6", "3.218872234"},
         {"4.81237385758E-6", "3.33460591977"},
         {"4.91242006309E-6", "3.45490630092"},
         {"5.014546165869999E-6", "3.57994979477"},
         {"5.11879540566E-6", "3.70991869101"},
         {"5.225211921139999E-6", "3.84500128958"},
         {"5.3338407686E-6", "3.98539203627"},
         {"5.4447279410199995E-6", "4.13129165578"},
         {"5.55792038754E-6", "4.28290728129"},
         {"5.67346603336E-6", "4.44045258009"},
         {"5.791413799989999E-6", "4.60414787441"},
         {"5.911813626019999E-6", "4.77422025668"},
         {"6.034716488199999E-6", "4.95090369846"},
         {"6.1601744230599996E-6", "5.13443915202"},
         {"6.2882405489599995E-6", "5.32507464379"},
         {"6.41896908852E-6", "5.52306535848"},
         {"6.55241539166E-6", "5.72867371303"},
         {"6.68863595894E-6", "5.94216941914"},
         {"6.82768846557E-6", "6.16382953324"},
         {"6.969631785759999E-6", "6.39393849275"},
         {"7.11452601772E-6", "6.63278813733"},
         {"7.2624325090099994E-6", "6.88067771379"},
         {"7.4134138826099995E-6", "7.1379138633"},
         {"7.56753406337E-6", "7.40481058966"},
         {"7.72485830511E-6", "7.68168920691"},
         {"7.88545321822E-6", "7.96887826518"},
         {"8.04938679789E-6", "8.26671345301"},
         {"8.216728452890001E-6", "8.57553747479"},
         {"8.38754903495E-6", "8.8956999019"},
         {"8.56192086874E-6", "9.22755699586"},
         {"8.739917782559998E-6", "9.57147150229"},
         {"8.92161513952E-6", "9.927812414"},
         {"9.10708986948E-6", "10.296954702"},
         {"9.296420501649999E-6", "10.6792790132"},
         {"9.48968719778E-6", "11.0751713331"},
         {"9.68697178616E-6", "11.4850226129"},
         {"9.88835779621E-6", "11.9092283598"},
         {"1.00939304939E-5", "12.3481881894"},
         {"1.03037769178E-5", "12.8023053398"},
         {"1.0517985916E-5", "13.2719861468"},
         {"1.0736648183699999E-5", "13.7576394791"},
         {"1.0959856301399999E-5", "14.2596761352"},
         {"1.11877047746E-5", "14.7785082"},
         {"1.14202900733E-5", "15.3145483628"},
         {"1.16577106731E-5", "15.8682091977"},
         {"1.19000670968E-5", "16.4399024059"},
         {"1.21474619572E-5", "17.0300380225"},
         {"1.24E-5", "17.6390235898"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("n_interp")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("n_interp")
         .set("allowrand", "on");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func()
         .create("k_interp", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("k_interp")
         .set("table", new String[][]{{"2.0659999999999998E-7", "1.98591656445"},
         {"2.1089508319E-7", "1.97534779229"},
         {"2.15279458439E-7", "1.96597777856"},
         {"2.19754982075E-7", "1.95853718525"},
         {"2.24323549012E-7", "1.95347479861"},
         {"2.28987093564E-7", "1.95092054945"},
         {"2.33747590255E-7", "1.95067979422"},
         {"2.38607054657E-7", "1.9522622682"},
         {"2.43567544248E-7", "1.95494272809"},
         {"2.4863115927599996E-7", "1.95784420876"},
         {"2.5380004365499994E-7", "1.96003112281"},
         {"2.59076385867E-7", "1.96059922867"},
         {"2.64462419893E-7", "1.95875241037"},
         {"2.69960426156E-7", "1.95386083963"},
         {"2.75572732488E-7", "1.94549986044"},
         {"2.81301715116E-7", "1.93347278078"},
         {"2.87149799665E-7", "1.91782321271"},
         {"2.9311946218899997E-7", "1.89884348931"},
         {"2.99213230217E-7", "1.87708470227"},
         {"3.0543368382399997E-7", "1.8533704822"},
         {"3.11783456723E-7", "1.82881024922"},
         {"3.1826523738099996E-7", "1.80479849309"},
         {"3.2488177015399996E-7", "1.78297652266"},
         {"3.31635856454E-7", "1.76512629255"},
         {"3.3853035593199995E-7", "1.75296935729"},
         {"3.45568187689E-7", "1.74786631426"},
         {"3.5275233151199993E-7", "1.7504582283"},
         {"3.60085829136E-7", "1.76035121563"},
         {"3.67571785533E-7", "1.77598261657"},
         {"3.75213370223E-7", "1.79477454227"},
         {"3.8301381862099995E-7", "1.8135693006"},
         {"3.90976433402E-7", "1.82921617912"},
         {"3.99104585903E-7", "1.83912927233"},
         {"4.0740171754799997E-7", "1.84168456543"},
         {"4.15871341306E-7", "1.83641651484"},
         {"4.2451704317999997E-7", "1.82404632603"},
         {"4.33342483722E-7", "1.80640361052"},
         {"4.42351399584E-7", "1.78630032214"},
         {"4.5154760510399996E-7", "1.76739565371"},
         {"4.60934993913E-7", "1.75405729533"},
         {"4.70517540592E-7", "1.75117198333"},
         {"4.80299302349E-7", "1.76378471582"},
         {"4.9028442074E-7", "1.79639799686"},
         {"5.0047712342E-7", "1.85189192283"},
         {"5.10881725935E-7", "1.93046771509"},
         {"5.215026335489999E-7", "2.02941584261"},
         {"5.32344343107E-7", "2.14408290908"},
         {"5.434114449429999E-7", "2.26939106271"},
         {"5.547086248179999E-7", "2.40097284195"},
         {"5.6624066591E-7", "2.53559991916"},
         {"5.78012450835E-7", "2.67112775467"},
         {"5.90028963715E-7", "2.80625688674"},
         {"6.02295292289E-7", "2.94028330861"},
         {"6.14816630068E-7", "3.07289808958"},
         {"6.275982785319999E-7", "3.204042941"},
         {"6.40645649374E-7", "3.33381143007"},
         {"6.539642667949999E-7", "3.46238342848"},
         {"6.675597698389999E-7", "3.58998261813"},
         {"6.814379147839999E-7", "3.71684963608"},
         {"6.95604577575E-7", "3.84322568932"},
         {"7.10065756315E-7", "3.96934309571"},
         {"7.24827573806E-7", "4.09542033789"},
         {"7.39896280135E-7", "4.22165999327"},
         {"7.552782553289999E-7", "4.34824843716"},
         {"7.709800120469999E-7", "4.47535658348"},
         {"7.87008198346E-7", "4.60314118084"},
         {"8.033696004889999E-7", "4.73174635512"},
         {"8.200711458239999E-7", "4.86130520764"},
         {"8.37119905712E-7", "4.99194135685"},
         {"8.545230985229999E-7", "5.12377036316"},
         {"8.72288092693E-7", "5.25690100961"},
         {"8.904224098449999E-7", "5.39143643137"},
         {"9.08933727968E-7", "5.52747509882"},
         {"9.278298846739999E-7", "5.66511166545"},
         {"9.471188805139999E-7", "5.80443769435"},
         {"9.668088823649999E-7", "5.94554227814"},
         {"9.86908226887E-7", "6.08851256644"},
         {"1.00742542406E-6", "6.23343421379"},
         {"1.02836916076E-6", "6.38039175994"},
         {"1.0497483045E-6", "6.52946895243"},
         {"1.07157190709E-6", "6.68074902054"},
         {"1.09384920857E-6", "6.8343149079"},
         {"1.11658964103E-6", "6.99024947049"},
         {"1.13980283269E-6", "7.14863564526"},
         {"1.1634986119099998E-6", "7.30955659417"},
         {"1.18768701137E-6", "7.47309582753"},
         {"1.21237827235E-6", "7.63933730999"},
         {"1.237582849E-6", "7.80836555206"},
         {"1.26331141285E-6", "7.98026568947"},
         {"1.28957485725E-6", "8.15512355256"},
         {"1.3163843020299998E-6", "8.33302572727"},
         {"1.34375109819E-6", "8.51405960937"},
         {"1.37168683271E-6", "8.69831345306"},
         {"1.4002033334699998E-6", "8.88587641506"},
         {"1.42931267422E-6", "9.07683859508"},
         {"1.45902717974E-6", "9.27129107334"},
         {"1.4893594310099999E-6", "9.46932594594"},
         {"1.52032227059E-6", "9.67103635839"},
         {"1.55192880799E-6", "9.87651653797"},
         {"1.58419242529E-6", "10.085861825"},
         {"1.6171267827699999E-6", "10.2991687034"},
         {"1.65074582469E-6", "10.5165348313"},
         {"1.6850637852E-6", "10.7380590705"},
         {"1.72009519439E-6", "10.9638415166"},
         {"1.7558548843899998E-6", "11.1939835286"},
         {"1.7923579956999999E-6", "11.4285877588"},
         {"1.82961998359E-6", "11.6677581822"},
         {"1.8676566246099998E-6", "11.9116001269"},
         {"1.90648402331E-6", "12.1602203034"},
         {"1.9461186190499997E-6", "12.4137268346"},
         {"1.98657719294E-6", "12.6722292853"},
         {"2.02787687497E-6", "12.9358386922"},
         {"2.0700351512299997E-6", "13.2046675927"},
         {"2.11306987137E-6", "13.4788300544"},
         {"2.15699925609E-6", "13.7584417033"},
         {"2.2018419049000002E-6", "14.0436197522"},
         {"2.24761680399E-6", "14.3344830281"},
         {"2.29434333425E-6", "14.6311519989"},
         {"2.3420412794899998E-6", "14.9337487993"},
         {"2.39073083481E-6", "15.242397256"},
         {"2.44043261516E-6", "15.557222911"},
         {"2.4911676640499995E-6", "15.8783530446"},
         {"2.5429574624799997E-6", "16.205916696"},
         {"2.59582393804E-6", "16.5400446837"},
         {"2.64978947414E-6", "16.8808696226"},
         {"2.7048769195800003E-6", "17.2285259404"},
         {"2.76110959812E-6", "17.5831498916"},
         {"2.81851131845E-6", "17.9448795695"},
         {"2.8771063842E-6", "18.3138549153"},
         {"2.9369196042699995E-6", "18.6902177253"},
         {"2.99797630331E-6", "19.0741116548"},
         {"3.06030233246E-6", "19.4656822198"},
         {"3.1239240803E-6", "19.8650767947"},
         {"3.1888684839999997E-6", "20.2724446074"},
         {"3.2551630407199996E-6", "20.6879367303"},
         {"3.32283581931E-6", "21.1117060677"},
         {"3.39191547211E-6", "21.5439073394"},
         {"3.46243124716E-6", "21.9846970594"},
         {"3.5344130005299997E-6", "22.4342335112"},
         {"3.6078912089699998E-6", "22.8926767167"},
         {"3.68289698284E-6", "23.3601884018"},
         {"3.7594620792599997E-6", "23.8369319549"},
         {"3.837618915559999E-6", "24.3230723814"},
         {"3.91740058299E-6", "24.818776251"},
         {"3.99884086078E-6", "25.3242116392"},
         {"4.08197423038E-6", "25.8395480625"},
         {"4.1668358901E-6", "26.3649564062"},
         {"4.25346176999E-6", "26.9006088451"},
         {"4.34188854708E-6", "27.4466787567"},
         {"4.43215366088E-6", "28.0033406265"},
         {"4.52429532923E-6", "28.5707699445"},
         {"4.6183525645400004E-6", "29.1491430937"},
         {"4.714365190209999E-6", "29.738637229"},
         {"4.81237385758E-6", "30.3394301469"},
         {"4.91242006309E-6", "30.9517001453"},
         {"5.014546165869999E-6", "31.5756258729"},
         {"5.11879540566E-6", "32.2113861689"},
         {"5.225211921139999E-6", "32.8591598904"},
         {"5.3338407686E-6", "33.5191257296"},
         {"5.4447279410199995E-6", "34.191462019"},
         {"5.55792038754E-6", "34.8763465244"},
         {"5.67346603336E-6", "35.5739562259"},
         {"5.791413799989999E-6", "36.2844670861"},
         {"5.911813626019999E-6", "37.0080538055"},
         {"6.034716488199999E-6", "37.7448895647"},
         {"6.1601744230599996E-6", "38.4951457535"},
         {"6.2882405489599995E-6", "39.2589916857"},
         {"6.41896908852E-6", "40.0365943015"},
         {"6.55241539166E-6", "40.8281178548"},
         {"6.68863595894E-6", "41.633723588"},
         {"6.82768846557E-6", "42.4535693921"},
         {"6.969631785759999E-6", "43.2878094535"},
         {"7.11452601772E-6", "44.1365938879"},
         {"7.2624325090099994E-6", "45.0000683603"},
         {"7.4134138826099995E-6", "45.8783736927"},
         {"7.56753406337E-6", "46.7716454592"},
         {"7.72485830511E-6", "47.6800135698"},
         {"7.88545321822E-6", "48.6036018426"},
         {"8.04938679789E-6", "49.5425275664"},
         {"8.216728452890001E-6", "50.4969010538"},
         {"8.38754903495E-6", "51.4668251858"},
         {"8.56192086874E-6", "52.4523949503"},
         {"8.739917782559998E-6", "53.4536969744"},
         {"8.92161513952E-6", "54.4708090531"},
         {"9.10708986948E-6", "55.5037996756"},
         {"9.296420501649999E-6", "56.5527275519"},
         {"9.48968719778E-6", "57.6176411403"},
         {"9.68697178616E-6", "58.6985781795"},
         {"9.88835779621E-6", "59.7955652274"},
         {"1.00939304939E-5", "60.9086172084"},
         {"1.03037769178E-5", "62.0377369732"},
         {"1.0517985916E-5", "63.1829148725"},
         {"1.0736648183699999E-5", "64.3441283502"},
         {"1.0959856301399999E-5", "65.5213415561"},
         {"1.11877047746E-5", "66.7145049842"},
         {"1.14202900733E-5", "67.923555139"},
         {"1.16577106731E-5", "69.1484142333"},
         {"1.19000670968E-5", "70.388989921"},
         {"1.21474619572E-5", "71.6451750702"},
         {"1.24E-5", "72.9168475779"}});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("k_interp")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").func("k_interp")
         .set("allowrand", "on");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").info("category").title(null);
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").info("category")
         .body("Electromagnetic_models");
    model.component("comp1").material("mat3").set("sys", "none");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "na", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "na", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "lb", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(400[nm],1[nm],900[nm])", 0);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(24, 25);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").automatic(true);
    model.component("comp1").mesh("mesh1").autoMeshSize(1);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").contribute("physics/ewfd", false);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();

    model.label("my_custom_lspr.mph");

    model.component("comp1").physics("ewfd").feature("wee1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("ewfd").feature("port1").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("port1")
         .set("n", new String[]{"na", "0", "0", "0", "na", "0", "0", "0", "na"});
    model.component("comp1").physics("ewfd").feature("port2")
         .set("n", new String[]{"nb", "0", "0", "0", "nb", "0", "0", "0", "nb"});

    model.label("my_custom_lspr.mph");

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
    model.sol("sol1").feature("s1").feature("p1").set("pname", new String[]{"lb"});
    model.sol("sol1").feature("s1").feature("p1").set("plistarr", new String[]{"range(400[nm],1[nm],900[nm])"});
    model.sol("sol1").feature("s1").feature("p1").set("punit", new String[]{"m"});
    model.sol("sol1").feature("s1").feature("p1").set("sweeptype", "sparse");
    model.sol("sol1").feature("s1").feature("p1").set("preusesol", "no");
    model.sol("sol1").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol1").feature("s1").feature("p1").set("plot", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plotgroup", "Default");
    model.sol("sol1").feature("s1").feature("p1").set("probesel", "all");
    model.sol("sol1").feature("s1").feature("p1").set("probes", new String[]{});
    model.sol("sol1").feature("s1").feature("p1").set("control", "param");
    model.sol("sol1").feature("s1").set("control", "wave");
    model.sol("sol1").feature("s1").feature("aDef").set("complexfun", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s1").feature("d1").label("Suggested Direct Solver (ewfd)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("Electric Field (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
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
    model.result().dataset("grid1").set("data", "dset1");
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
    model.sol("sol1").feature("s1").feature("p1").set("pname", new String[]{"lb"});
    model.sol("sol1").feature("s1").feature("p1").set("plistarr", new String[]{"range(400[nm],1[nm],900[nm])"});
    model.sol("sol1").feature("s1").feature("p1").set("punit", new String[]{"m"});
    model.sol("sol1").feature("s1").feature("p1").set("sweeptype", "sparse");
    model.sol("sol1").feature("s1").feature("p1").set("preusesol", "no");
    model.sol("sol1").feature("s1").feature("p1").set("pcontinuationmode", "no");
    model.sol("sol1").feature("s1").feature("p1").set("plot", "off");
    model.sol("sol1").feature("s1").feature("p1").set("plotgroup", "pg1");
    model.sol("sol1").feature("s1").feature("p1").set("probesel", "all");
    model.sol("sol1").feature("s1").feature("p1").set("probes", new String[]{});
    model.sol("sol1").feature("s1").feature("p1").set("control", "param");
    model.sol("sol1").feature("s1").set("control", "wave");
    model.sol("sol1").feature("s1").feature("aDef").set("complexfun", true);
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "mumps");
    model.sol("sol1").feature("s1").feature("d1").label("Suggested Direct Solver (ewfd)");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");

    model.component("comp1").material("mat1").label("Surrounding");
    model.component("comp1").material().remove("mat2");
    model.component("comp1").material().remove("mat3");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Dieletric");
    model.component("comp1").material("mat2").selection().set(4, 6);
    model.component("comp1").material("mat2").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"nb"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Metal");
    model.component("comp1").material("mat3").selection().set(5);
    model.component("comp1").material("mat3").propertyGroup().create("RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"n_interp(1[1/m]*c_const/freq)"});
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"k_interp(1[1/m]*c_const/freq)"});

    return model;
  }

  public static void saveData(int number){

    try{
      FileWriter fw = new FileWriter("myDATA.txt");
      PrintWriter pw = new PrintWriter(fw);
      pw.println(Integer.toString(number));
      pw.close();

    }catch(IOException e){
      System.out.println("Faking Error");
    }

    System.out.println(Integer.toString(number));
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

  public static void changeParams(Model model){
    model.param().set("gold_surf_wd", "25[nm]");
    model.component("comp1").geom("geom1").run();
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
    changeParams(model);
  }

}
