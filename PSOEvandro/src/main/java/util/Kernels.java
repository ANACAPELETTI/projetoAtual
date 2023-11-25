package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kernels {
	
	public static List<List<List<Double>>> listaDeMatrizes(){
		List<List<Double>> listaListaKernels = new ArrayList<List<Double>>();
		List<List<List<Double>>> muitaLista = new ArrayList<List<List<Double>>>();
		listaListaKernels.add(lista1);
		listaListaKernels.add(lista2);
		listaListaKernels.add(lista3);
		listaListaKernels.add(lista4);
		listaListaKernels.add(lista5);
		listaListaKernels.add(lista6);
		listaListaKernels.add(lista7);
		listaListaKernels.add(lista8);
		listaListaKernels.add(lista9);
		listaListaKernels.add(lista10);
		muitaLista.add(listaListaKernels);
		return muitaLista;
	}
	
	static List<Double> lista1 = Arrays.asList(
			13.3838423139927,-25.8934093587051,-8.52342349654229,-2.46882121963843,-17.5663199987202,3.29352696862014,-13.9961960809537,-86.401146696249,10.1846020811491,35.5532814354841,10.5447160203475,23.2338258053788,29.6354690364882,-2.97097183031121,2.98069561104861,-1.14067388029928,184.013339945651,-9.90814112042621,63.0394120989783,-13.787853462929,33.399135768966,-14.7573061806677,-9.7877389512116,7.98956272844043,2.44793708900994,-5.8492660847694,-1922.98902983825,2.69625446883333,1.47965402465539,230.854389720493,3.36782295181169,-20.342579385996,-12.2278899507458,-5.06399114816161,-10.2782325087412,-14.1964735034243,4.47303352579254,55.2070428045043,0.157510474321698,18.624559354075,-11.4663969645607,-2.22863286394058,-2.76611826751128,25.8978684550112,5.45488616097616,-13.1013479568581,1.29365322285885,-19.4694195947691,-4.13722872600501,-8.69399602826083,-1.19842118073999,63.09789835885,-1.58411286172666,2.9851254240465,12.4068570929285,-50.4341381546786,-14.6625161295356,11.8108722733307,-20.4055697500927,-3.20133703447212,11.5481358042092,-9.03204618073367,68.4940300558627,13.884982621556,-5.87659612469782,-3.56604844534059,12.6698303540096,-12.0107493993371,-4.39072861448466,-0.867251015570838,84.0734018016414,-4.75049997870463,-7.58173939156488,0.00807331378546639,-1.25663957744935,-4.02781779685028,-10.4933139107408,-1.21492780454713,-2.87680516016743,-14.6552743396166,-10.4177372356807,-30.9339925401921,-20.9266366285731,13.9795876006994,2.74557429410829,11.1734705555955,10.1670029895605,-13.1987462949455,191.028878573275,71.0154706459937,14.7238524071065,-70.4514532468553,11.4868767286191,19.1074444355155,-4.84926260471877,7.76666404671038,58.3336322716865,-50.2114966072415,8.72480711762738,-4.36229069904524,-5.20783646668898,-11.1994622275224,-13.6043633026642,-6.14909451983063,12.4040787785189,13.6827501012002,10.1510054890983,11.3905195928515,5.67830110624214,13.187597798527,-170.005871216769,-68.4216009886918,0.698338204947774,-115.845838400902,-4.62020402015956,-6.02516098117965,99.6019654171668,16.0912479662633,-9.08305432809142,-4.14029295571103,-53.279599056318,3.31954259370076,2.88454456461038,-12.6709748013724,-15.0047751665481,5.9537710896984,-10.4104946399338,-27.9562366473885,5.87882014103284,-16.1208328570965,5.56818301578081,5.8906039054068,11.7926547945415,-3.25066544267048,-148.033678011809,-3.110738056572,18.2544061633174,-0.0524758280631723,-19.9869922392659,-0.0671364789205738,-12.4224814657149,-5.62429655258885,-15.8126678161047,104.529893570144,5.16214553970284,120.769886836931,9.88062948971069,-1.55278266492107,-0.276443339665775,-1.64948587083878,-7.29932865515398,-169.256376223082,17.3321783120079,-6.28369630957996,15.867844309466,-10.4802563535985,253.690402805966,-27.5074009189317,10.5655170360668,-73.5535602203807,-7.41496652531541,3.0909029699333,12.5474286916365,-10.8031423504623,-46.5521897657251,3.00458811447002,6.79431801670131,-5.98221916260267,0.953060760101176
			);
	
	static List<Double> lista2 = Arrays.asList(
			1.51675735256934,7.83452356759125,-1.9552967730288,-15.9749328810728,-3.8238350513143,11.848497753383,3.2692266733459,4.29076654843896,-5.71425938212094,-12.462945300673,25.298885652715,-55.455161565729,10.8404184287964,-25.6338637644638,-2.49879777275593,-1.79944799666425,37.5821075526309,-9.48222569832644,-7.608454404874,-9.01398279994071,11.5724128540602,-8.91851893709508,-1.81529127645218,-3.62763414254498,20.5224740730673,22.8055553456528,-6.85598880387239,3.95824174730558,-7.9062718891201,-3.21418514328837,-536.978726567479,-5.64486838049582,-19.3105970809258,-63.7576950855422,1.39890958029051,-504.368949657175,-0.884445300123988,36.359299004616,45.1888486346905,-25.0117456407238,160.522548017255,9.56426328743289,-10.3308826671011,-11.7724207169088,-12.1616174557042,6.08068644061646,-4.9455221602191,-5.3608033262191,-11.589145156311,8.27477585690099,10.8619497200471,68.3799285353624,-41.7984062515265,-12.7254955388894,-0.952271635032196,-203.804380926002,-8.76278421749897,-13.8923201269047,16.603482913979,-9.89176584613399,-4.3854261802041,2.28865992668233,32.7794803623598,29.9712339945437,-7.68692774862076,8.18929131394203,-5.33804360563702,-12.5427062646081,-12.2078405344317,-5.74386621864527,-66.3298355812286,-12.4785103894282,-48.5882184631093,-18.5308800159094,-1.85940852910322,-9.4086025161489,40.6962264224679,9.66733585822273,-13.252367058291,-15.469794178651,8.04399735237602,-0.500244635288301,9.35388959147595,-31.7837673753038,8.62320834954634,106.612197486218,1.61491262132934,20.0059550814656,-0.240779144728807,-0.0185692288356526,7.05313850589871,6.85165728717164,37.0964000219537,115.367323860262,47.1269633007575,-30.1119172838858,-5.64028047679393,-2.63103949868404,40.0153694547669,-17.8693804895965,-0.910983886289556,102.52803715212,-77.8709504579688,-1.70729109256162,-21.9749601658532,11.8548870595495,1.03688824916917,-7.74108290336808,-5.96796434517903,-3.1656616153658,-2.02538223099137,23.9512970321135,-163.015234042008,-4.24237058111296,-102.9702584396,-19.8758092618942,-95.0895133409527,-0.657831499263306,12.7385711923937,33.7902552495467,9.03627786964297,-13.218814639533,24.7541555644907,49.9607103714358,257.352275948006,27.7205836873103,4.5451317450166,-457.257580116108,-3.66359751705873,21.8050249925281,235.951632603079,-4.32332265300955,-7.59371045574792,-14.3503475201893,-16.9842310836883,-12.7883745722935,10.7687338027535,-4.15740177031448,148.332147673792,61.123868081676,-21.0565578761001,2.75252633472819,-64.4760815847808,-1.66327066023279,9.74670538415616,-13.7746163001587,-99.3194253879055,4.69472694682493,-7.67429149088562,10.5763024143992,-4.55485187413569,7.4747600251233,12.1790646950197,-90.2451659290403,-34.0370784272468,19.5471158593921,81.0094755404815,61.0923113902578,16.4223878308242,-9.68889750225348,83.0915633286209,-73.7601302311573,-3.66181023174243,-21.9586547062911,13.4163581368995,-7.63381145267003,12.4634870863716,51.116904386862,-5.55944222377469
			);
	
	static List<Double> lista3 = Arrays.asList(
			13.3741381089476,-481.008803877952,12.2499276797861,-13.4549125709572,13.4517973128181,4.29798578024651,-3.26923769632639,7.60764793066466,-4.97331994579768,-6.33782412090763,-122.922059843172,33.7364359727585,31.6051060109302,16.8056477682093,5.04470220932038,-24.6163031964401,10.127444116254,-102.063778625268,-0.204409066748529,-0.495977633262,-13.886055629804,-28.5451016061243,-9.80911403428351,9.87144105878273,3.9221066745371,124.170546535853,-10.6164429736236,-21.6161987115395,-92.6234135166117,35.1865195385791,4.05813743470892,2.18273079384389,4.48468083067799,35.6336077317682,18.807553120883,-5.67312480669298,24.6286055929766,10.9930457170835,-36.2030692109561,-29.6679159938844,-17.2026820670836,4.97261999092086,-9.38532442024852,-4.90625539029862,10.0827117215004,-14.467488054585,-22.0801358998324,3.65688280669538,3.82648703059868,-8.5393030696279,-38.6057549082386,-1.11672736806129,3.84700574195566,-2.95700509551313,4.98394588187631,8.23626056190592,-19.2101912293733,-1.06728149016581,5.40016908053045,-11.7383023496701,-96.335640114943,25.5769785047717,-0.989088471611233,-210.466289608082,44.0764507291252,-26.992445508953,20.3274589290013,17.5452204128467,-51.275148731534,3.20222046555868,-14.3181583408985,-19.7103331969551,24.0077021317221,2.21341659284015,-18.3408663291442,-2.91751774286273,-4.05919479024901,11.1733033859882,-75.5797220590356,6.06523166327117,-99.0069333118969,-0.692404176219149,1.91552544526881,-6.17382213901274,-0.672692680067637,2.34944387695308,28.6898981698593,-32.4053128987995,94.037984161409,10.4772674674414,8.5400891287185,-22.0422030258661,8.09972148056197,42.0407880458706,8.30114847748002,-15.4142702732703,0.614011140179695,-0.864791252493644,4.49987820842264,3.11678489927552,6.96743152005851,-5.11008588183924,-2.74598845123493,-41.4395332586742,5.20399419729384,310.07578681399,-3.97987292674854,-15.8200501781714,18.0362934198367,4.56063940040015,-195.146038309748,-2.56922352153346,0.676083733890787,22.6792220563835,-3.13889766165739,-1147.00781443696,-0.900442963902343,-9.97853482568363,22.2407085818914,-6.81203667743434,6.88771744699075,22.2320630111649,-11.0754939708496,2.25580833212096,105.428852544284,-32.5624497843678,-1.08128513039341,9.28276229894921,65.0419680273259,-11.7727691527082,-28.0750342057204,7.48058962189796,-11.0516786704875,-85.9251847997099,-16.0725564756805,10.7700464548298,4.49435180352155,2.15481228730697,3.50764225909788,-9.78922435503367,6.15170738921774,11.3531176298832,32.5390039889949,-48.6055194568592,5.11144430992281,-16.5785335203829,-11.9789250278303,55.9691644385476,26.5514638482728,-3.98052190791135,3.60384154047618,11.5799468525289,1.67492108301021,16.4324864890538,-0.681577845957201,-8.07268044489107,-20.7637613286269,10.045220216176,-97.2243126988574,31.8256356614693,24.5398860869031,-80.0461472658079,-18.0605734397397,-58.1195939368028,16.4901391304858,-0.775079365572264,-0.720606196728647,25.4904793344885,0.0641398302848879
			);
	
	static List<Double> lista4 = Arrays.asList(
			-9.43574891103259,79.5369120618655,18.5701284311015,3.54970065210095,24.8313100403634,-4.76110484235688,-11.7705790269323,-6.7744334739746,0.786965429114187,-30.839046258767,2.88974181177308,-3.27133609705505,-15.1613038153967,12.5220806829048,2.49860538828434,-131.851500834085,3.64182297077996,18.9945712822863,53.6056413614262,1.02448765251906,51.2053115128733,1.63258542122349,-5.5098206630574,3.2812638866259,-17.7531455109164,-7.10234205328317,6.9579600796383,-10.1294698378016,-1.08424788641088,-1.75631180483747,-12.2589544004261,-1.00647014677684,0.744603025724897,2.02031233833209,-11.1745359076195,0.699390380722123,-47.4329218842584,-1.4621115049891,-7.49433325155026,10.9051979320576,-0.0268800787901461,15.8054052972122,1.23331682408291,16.7277050773592,-0.0592691296349013,-2.48802190357749,-12.2226442944941,7.8901862550963,-14.9431657238435,-35.3125786069301,-2.32707838155465,303.077733921661,12.5520110251707,2.54465095347701,-46.9834657206075,-14.0651046741379,-3.95949841717134,9.60243302736047,13.6584104739546,-85.888471457911,-8.55879155234289,-23.2934381228863,32.9394074489655,-6.62555219802922,1.11616007467123,-291.497484332001,29.2540722578075,13.7525881689277,-6.2023398608841,-32.374094032112,116.731240303178,-2.67107831250669,-20.0833240260405,7.17654379614115,8.76894561575666,-7.01939172148236,-187.912667557702,-113.416045304811,-0.827891697185396,10.3585364733268,-30.4532931049627,-36.4454252522474,-114.658787080611,-0.893120092118461,1.31819463639922,50.6251937544029,-24.94201864946,18.3158914178413,56.7809552350415,-9.16971274900416,-114.995410047171,270.348564609543,-3.34401054194681,7.15808205609522,5.51043245227308,10.0478278313881,-8.62532028738105,4.3758327854419,-8.95360931841343,-60.4170292978622,1.21919570665722,-0.0739490043003653,-59.3979012466227,-20781.3663749046,0.747603915195976,-27.8678131935168,-5.46913544948651,-7.89978934606529,8.74004348571536,24.3352247192185,22.7590898903049,25.1058471540147,-2.28579040335466,-23.5708125345879,1.36003863257562,194.683731594836,-13.2691674597427,18.9116756486876,11.8827332310869,10.8003665891209,5.11875982290231,1.56827854610119,-10.4161228356443,6.83776349520337,10.120481073223,1.14671141563379,0.200376474694546,19.32700283934,102.341457639604,-29.2251201750411,25.6461136086869,-91.3492598774337,-14.9124641446686,0.783430168769452,-91.7974766896993,3.86269271129478,-13.4270014296964,-7.96985764919313,-20.7520686851517,3.374176375011,-5.98862957919535,-62.9693230291956,3.4165625347336,-27.4000793687813,-7.34665805697033,5.63124973052071,-26.5374944102102,9.63946883370924,50.3247364437361,-467.825145864082,2.82509978873062,-4.05228910890256,-12.7532360407149,3.90905493816011,-36.5408085516788,-18.1907763838776,-27.3949627471514,-13.728327549681,18.3390013598756,6.63013086230592,-26.0211220350739,-16.5202743310219,-1.90503135627286,-17.0788372775474,57.8009793840358,-4.449552939988,8.68544909028592,-9.36638739645789,-3.84388887798128
			);
	
	static List<Double> lista5 = Arrays.asList(
			-0.531758321387669,-47.3550608526201,-0.832670522122541,39.6604665228251,-3.93474764339182,-24.7363448585786,-12.4566750261197,2.4621493702001,1.37473864736223,2.52498096018135,-0.630202837689272,-9.5598180196839,6.5046045804862,3.68923810723819,-5.18808116482532,3.64155745675077,37.3168811128072,20.3617686692642,37.6868400034194,24.4691780593993,-11.5424699142018,9.87833068538663,19.2332728309756,-18.1871534384903,-29.0934263319344,14.8724401675166,1.21146621438483,2.61825253858132,12.803957033032,2.83408860003322,-46.9586103288924,3.99914406274914,10.5397111207116,-13.1951686922494,92.5120481329275,-17.4039846369171,-60.1084192180346,1.86955254732941,16.8567067189668,-26.3730862648597,-6.79051450629706,-58.3392213554716,6.08927825295779,-11.1436485049926,-5.4557282608599,-6.89513250972336,-2.50341209399175,-10.9486519343311,-8.39918663283477,12.1276777957133,-27.6937085414742,-10.5085514017811,-44.2963129890381,9.20556937711578,-98.8679497231713,5.06806252188779,5.87887176082843,-31.8005403711363,-33.0378343963448,4.9904170026335,9.63536151871035,3.27677797755298,23.5720015927763,2.26832961490438,14.2488022757495,196.697663733474,-54.9608325835034,-7.24705007413648,-11.7258465993403,78.0038040670954,108.590059099353,57.7578326151875,-24.5814379528684,45.998306807715,-27.0124152941821,13.9813965749285,2.10511334997319,4.39172238322393,40.4212190214891,-35.0704618981461,-2.04269667355756,1.85371410230533,-1.18781847096428,-2.54592970117322,-13.4201965687246,-427.753778188496,5.27439377582728,7.54964421206621,-4.12962137513119,299.688825426267,2.92742166439144,13.9400285258085,-5.91754405138731,2.72455632707685,35.0682917460893,35.3516969114911,1.32577566246625,-12.0487835140964,-21.4698199009713,-6.19787178312133,-50.5260219731623,-12.7361341842268,-6.2734255127221,-5.80840399890642,9.87525451815991,3.49325723965473,-12.2484147168941,-48.5487324170144,25.8555027883413,15.3795408899234,13.0125978060196,16.3047473645705,13.7578256351029,-7.26052468937993,24.8208432589198,-3.40312424260049,0.985951893668081,-2.44547493814407,18.3929029238745,-24.5836856868813,407.88128225494,18.5962729907008,-22.1019257806866,44.8372626279372,-611.282563406535,21.3327729016964,2.2992359780324,1.04640797438332,9.95126104576447,1.56383676056876,9.3280061198402,0.646282380838365,12.8286092119022,30.8971587043997,19.0456453689413,31.8218061702626,-1.58139042205337,93.9319509325658,-7.01577365388292,-19.8636575308903,2.66839646938045,15.8059429722071,1.58608962736079,-0.928147594202358,4.75029689345134,-25.1470015274842,71.0149531189017,79.506551052398,-11.9014022573312,-6.58899808448817,7.97260188793337,-9.40515040203547,0.312262264271841,-19.2002498103398,-6.78336617487324,-35.3203224777096,-31.880129433872,-10.6748973920134,-0.948899055274809,10.1377404667681,2.29358405687709,-48.0853122845208,0.513270868069933,6.54615092687399,-2.45042501493534,-30.2676867949198,-2.69708510879266,-9.29358480589598,-2.94170865042717
			);
	
	static List<Double> lista6 = Arrays.asList(
			-29.1321832575303,2.10380806790715,-96.7368982902263,-5.72677446579658,-7.73896477812446,1.79200350565664,15.4478297213017,157.156893401849,-10.1050700972672,23.1156345311885,118.241381682025,-0.133315420557993,271.714160775422,-1.68548762527404,1.7377768044356,-2.87014735822482,-6.36716317305449,41.9268632489879,-7.46252824469631,13.5711079708292,3.53965651319052,-11.5471315804626,-10.0255349087474,5.8264319548003,13.8724452441717,5.43045059413427,4.63703649720059,30.4992201914053,-7.98430810884803,7.95607249634675,117.192471761015,81.720890485172,-4.30057355055203,-0.566590759117914,3.65632133323021,-0.0138670822588035,47.0471208864337,38.4119627519997,2.05954603866826,106.652149816065,-4.75638266279429,11.3357582754869,4.35621610311035,-3.20963633643423,-3.03819383583496,-5.03730822126487,23.0461378724418,-35.9054482519077,3.18322507918188,-30.8242450908157,-2.21849420617301,-1.8846369500586,21.355999191875,5.06733005325469,-1.35290734831071,-1.7617677034538,63.6556489534082,9.61489704954446,-26.0773820654932,21.5981816212753,-20.052700087065,28.8139916701827,-22.5249089607329,-11.6678940244582,-96.1889499767212,-3.25568497027706,-47.5434606056194,-7.73422622476279,-19.834564453975,10.7011747111959,-11.837633105521,41.0976383068037,-1.0678055628349,-212.719596059422,-15.95104210073,60.5029174466507,-11.366318741331,-12.5398077364919,5.5047767319005,247.913198672245,-144.872448081744,-2.62520636301244,-25.0536710799394,-2.68801610494226,0.855655647554477,1.57786594122284,-14.0610756325885,1.05910274444352,4.35803753009901,-11.1030548060375,23.0773462438162,-3.23285432216666,2.26474970896231,0.865954857404192,10.9585388972502,-3.46616962403307,33.7850788458009,32.2815352210675,-1.4403844985904,190.127186464644,-12.8353731172901,-11.2621072891955,6.32035895078302,-16.1962836398642,-32.9248032599829,-0.101661988623211,2.35174392208259,-54.6344018405728,2.23545888379299,5.09137718032924,-0.720061503526121,-50.0511492754874,-78.0001378980027,-25.9417231188199,-32.9757424179153,55.4543944557181,18.1869744816996,48.5245674019583,-16.8368538014582,-187.396582343396,-30.4474261859237,0.558365322503664,106.354018222015,-49.823935011039,9.75512706806448,-0.0791197549053772,-4.13469210897021,2.21843982056348,-3.26034709611238,7.2307547590981,3.61616567385823,-37.4385644569208,-121.14411915535,276.771491727916,8.02464569993299,-2.12447898592439,-27.2221751215968,12.8094307154511,-7.35043401009559,6.50478734525783,7.21523222834215,59.1670669475796,-1.12943475355517,127.465975256094,16.6485946449435,-26.9448135785645,130.253044963052,-5.1787507050751,-34.0081789655825,-17.0667652688138,0.670714159209239,-4.33929963287705,-11.6949423773938,-11.3365590973677,18.7194472480317,-333.532887722011,-11.5915244502787,12.7810358735317,-74.2849717684906,7.88508385318353,-10.1799418157396,54.5751078555435,-4.24073055298678,-2.15482010020152,-0.361311331981833,0.643813301025389,-4.54792688015269,9.1790896984186,30.7969759269459
			);
	
	static List<Double> lista7 = Arrays.asList(
			-0.774395226090493,2.32726917923087,-22.7029785598698,-21.4332075485738,2.06373416925894,-827.290384417652,6.97224945275934,-7.04580799384433,9.49661000169752,-6.12896600110647,0.0918630417056454,-15.6671135384044,-6.96474992391759,8.32511403820985,-8.50040989745864,-16.7483011775166,4.54796901183661,-7.23597572302153,2.41423100943413,3.44374513428426,46.5363520651244,1.15765876584075,1.15067723473419,7.91487770828747,3.56737081460371,15.0043791873872,-2.74796616694524,10.7668365001474,3.20639751513597,23.7513617461017,59.2924112899137,-3.75677584121652,1.74733436010706,-113.413935374534,-50.7201012874987,26.944604639629,3.63115544505972,-6.03946919962128,22.6225914437433,14.9871369545597,116.168634796124,-58.2952337042685,13.8242824745984,90.0261163679784,-13.7721745633122,-18.6545435816147,-5.74104894909257,6.35235674985438,8.54491498202045,19.6342151413426,26.4508888488843,12.0258370916597,55.0697035945034,-10.8644225475199,21.7133361018108,-1.60675037829763,-97.9507120973429,137.838425491729,22.9789849760514,6.28493437238865,18.6714404245138,6.89631078165147,6.83550388403584,-1.22214584165776,-52.6848491676432,-2.3476808578699,-12.8844750298477,5.99605760046579,-7.48667779715965,49.8221034006336,6.18091150547467,3.94690297049171,25.9371061793909,-16.8035380008378,7.10497869776607,3.79412529968892,0.524149654147379,259.940189700106,-0.944920970774465,-4.58332369542526,6.45093592995651,3.99884851661213,4.12062129384473,4.90194233571775,-7.50612647651075,31.3468556178207,-10.2699617924066,-3.54850683691014,-257.351446996871,-32.8679119967608,-9.89266173117111,92.7895590131315,18.0918835065474,-8.74994208474281,-92.3312819662912,-3.38487501114127,9.31521521720047,-3.07457420300587,-8.38999505256297,40.1057000248708,-8.49602337983982,-75.7798622584828,-3.39600475704606,-0.929852680623654,-19.3132098309365,-30.8117903176579,10.0422302224521,-9.44500088540342,-0.587306551329831,2.23468483465824,-9.14949366687448,0.536843401810016,-3.19415665497162,3.45916611998544,-15.8726483846315,-51.6633529137844,3.70680267993654,-9.05656119287194,-56.9979589255241,-6.68454672316209,-8.7478197539655,17.0801505370251,1.94698056949002,13.1699739958654,-3.53404913207606,-10.0401583405777,3.93100053755005,-2.46954982701508,-139.810375271882,23.3202961256025,6.72909097203658,-23.9005026415883,7.52940844976374,-5.89864324670616,-55.8674072960705,-4.82775991388517,-31.4263504723784,17.3363068004637,18.7627865620716,16.5879607863955,9.16488881115209,50.7123546144689,11.6675216884245,-12.8277748183426,10.7145392541263,0.260217578452935,-338.709772495404,47.6280446512074,-2.44424933946019,-56.7431605255882,6.68456937450164,-1.24710013122972,-36.8401153366173,-1.4525370359106,-7.43867596918647,-0.492297362367514,-5.12763628183424,-12.8612684846827,-59.1892805022398,-14.6135853820658,-2.24628778376583,-5.02004292902649,-0.37797348162103,16.7294897334552,-17.9928241511419,27.6365880282002,151.929255788835,2.98614128022222,2.89534970858546
			);
	
	static List<Double> lista8 = Arrays.asList(
			-3.97022973882376,-0.424349288844,26.2226224176559,-29.5862524931649,1.9399495598356,3.96972388193858,2.94175459936913,-106.612860507726,8.27324944447039,9.60044992067283,-2.43488692746425,105.122898323199,-8.52473661959128,5.76056939788553,-29.2002468254118,-7.90848805518245,-19.1302699936163,-9.3669137588189,15.7640958969587,1.79006212413135,-17.4473563078391,18.5472401651586,-15.6047348068998,-21.4061911586934,-14.3435275927411,-1.8848873819931,4.28268640817929,-4.26551297836311,-22.6462693178974,59.4154626783587,-89.2076603529788,9.27011171320039,-4.85659273456921,-4.10732368819516,-2.2891812794975,27.2791192868805,24.6956837987123,-118.134596844473,27.6811018251845,-113.44360077477,-0.361197837038893,26.640742829717,-2.2120888169261,5.76789804094245,-10.6657039548237,-24.5764990664842,-8.52897167834576,-7.74120604073714,92.0383309564539,-23.8556203133267,-1.2858327435629,-0.725349071532692,1.67464098383755,4.0535119309693,-16.7388156260403,4.66860106122035,-3.67214181185318,16.8336499355434,-26.3648959798356,18.108059883868,-7.39544067101114,29.4881931200754,-0.980084313005712,-4.8585754109536,-1471.71717979947,12.0349898807585,21.9642679447836,2.15585972697959,-1.4857293272823,15.9656675654659,-2.80928591102742,-15.6254224575536,7.54175112644688,11.6341445158791,-9.8302715933353,26.0230884051628,-54.7514779909933,2.29459909915617,5.27689361753022,-19.2338441838147,-0.418445233666782,-24.7823444984382,9.7915243920394,-3.30996682490614,14.7165497058353,32.8812413814872,28.7926925217012,-9.61630994527193,-0.463974330996192,3.83107872666779,-1.93321378472729,-26.7476131239834,30.3760728242599,7.1779351081582,9.37774566169628,2.06671841124374,13.873730759486,-3.52482437938798,13.9046445431472,-13.6371305810467,-2.42386704091334,127.49015861583,26.8923270488556,-23.367625945144,-13.1191142031463,12.9522612549365,8.30413466484727,5.75748404776128,-3.45702051059433,6.7173393411162,6.48610311002562,14.12602620038,1.57954712559298,30.9503475644604,-9.31671033272098,-0.984445486224187,-20.4014751801056,5.12010514568444,-42.1619292165536,71.3953364366334,-7.4301196294732,128.476812740075,-0.179310630793512,-32.4229145102479,7.31964098560195,22.9070405581603,-28.6976478250349,4.60558611210034,-89.8930989613462,-0.47670706006129,-10.4016071657011,5.16250613985788,3.96605901095126,-15.716322626437,-4.9240885397356,4.20977183606783,11.458840606627,-75.5648339202211,-11.9104542834249,-180.66884537415,-11.4355998359268,-31.582062710011,7.47534430440486,-15.7645665689447,-3.40480385073038,-116.362097354482,24.2014863174145,13.5152227751622,-1.64053514165452,-12.5107589401257,8.96847285922773,12.8535039834655,21.5690663823654,0.250716226312418,-5.19774654330346,6.17164850238883,3.94140477017709,-31.2563200935882,-0.22828628622366,16.9757969366592,10.4470283390435,-2.2532802887352,-18.8099972680465,-8.73947682839753,-36.0664261717624,11.7570762736436,72.3433625608653,-14.8886864808889,9.39920956516966
			);
	
	static List<Double> lista9 = Arrays.asList(
			15.2611019076401,845.946962483162,-25.2015679047265,4.2134205590326,326.870384240613,-1.3067203907704,3.18317848964264,-30.8334194639107,9.04216316335601,2.68696618583719,6.75466379361208,-2.17501881937141,15.0506351327251,-5.4310023173484,0.361375319085109,16.106729667882,20.8610717870607,-12.6182234368422,-39.692973966596,10.180241302735,-14.3067513795558,-13.3083898878522,8.53468549419091,-29.4527845761947,0.248661963864267,55.8279144200751,-3.56416915957977,-5.67009778041659,8.23985667085654,2.41466139193296,-37.9184951257487,2.58407066848439,-1.9811638278551,-1.07052413317509,-0.695162197714692,-1.92429377322103,151.582614411855,-73.485523876848,-22.7392363278357,-2.26067543624643,-9.82097710438598,-26.0833907113672,6.77829195184949,5.83990783564746,-0.520532048877415,-17.0973306682949,-4.01366450200219,6.08085610466616,3.92536479402545,2.25070761561303,-23.8207410808272,-5.46006049928849,0.273482606663414,16.8429212307482,5.90183707595004,74.9250456165421,4.54413732185055,24.906946208714,5.24229416156859,-6.53843696709151,-1.95910463720938,47.4919879118929,-6.90370489243887,20.1307779453509,16.7443453697796,8.96449560268792,-7.45207944904422,0.90426530723025,6.3094311535159,-11.4623998557532,-53.157351120847,-69.9909414770081,12.9330586111952,13.1388166194236,-19.4739546452168,1.67211013819652,-9.68288246511494,-32.7499750534931,46.4252368362515,-49.31491511596,-3.0883554532064,21.9570577788802,20.9406790610265,11.3037978101203,-15.4131032799274,11.0762742961837,-4.06565127824058,3.03685720987953,10.3998386241537,55.7893513309207,-2.79708574301363,-65.4791384985032,51.6811446341563,24.9684364478564,-6.7226834809503,-6.26181311646343,-163.825900235374,-16.3662474345564,12.3538473489027,-25.2558096491049,-11.7387414610148,3.51690262343028,-11.747091203961,-14.7123361454995,97.5881804930897,-47.4968840677353,-22.0071058691493,12.9030401109608,-1.43672745915176,10.6653943792432,50.2817494689819,23.8198774807855,-22.2579009158936,-19.5790325022967,5.57667140840301,71.7810749484429,19.2649521856729,-1.26316942506584,-30.4746085315419,-4.82285462578862,-3.33645753207496,-2.6435158400452,-127.163588577073,2.47171024229573,10.6651447311979,-5.68125221500088,-38.3046371770524,30.8889591836397,5.01183938685148,6.47136901397268,-30.0139141593258,0.4931299292227,-55.0579753501724,1.77835615335574,-1.85077941006063,-11.691141460164,17.7361799654336,2.57977990248882,-4.41548408632173,14.4892930409721,-37.9692391372152,-16.671499848938,44.9653158894569,9.02775630242866,3.72775851525782,-3.38974253986503,-1105.05443112848,2.72939534756071,-24.0614406941083,8.57128154568512,-9.51043692849215,1.75638950417982,-2.3560747588892,-7.52637814059714,-117.048592585293,-4.84397812524063,14.9984144717228,-575.264428533332,9.50429057532177,12.99216300785,5.89453655925763,1.89617050885544,19.4714268473452,17.2878695373139,-24.5775967816477,61.8418485926303,-4.92861033511601,-54.9803997691265,-4.24719974524759
			);
	
	static List<Double> lista10 = Arrays.asList(
			5.3265080406602,-17.3143684501824,22.4911072086081,-14.0770151950813,25.2070136316558,334.352275493294,-1.31980080764865,1.32785054003254,-1.82272836644289,-13.7477173681353,-5.76625617661319,7.92234986508765,-33.7290319119883,0.735997284029148,28.564586745249,-4.6132672117,-4.32590727267993,-26.5320701263394,-0.874260112536731,2.32337512062731,38.5919748291404,-20.0823109592739,8.33021257540886,-24.6291954040406,-5.01955562499688,-1.7078345009061,3.0413583446904,203.922566505118,5.94011534146511,1.67692758532555,34.916934083847,5.04970769408985,-17.1513459673477,-65.5074517088739,8.90422151843815,2.07264378684017,13.7743951824196,-23.8285673933518,3.8785177742654,3.04190508090787,-111.238787274112,-49.7240887362128,-85.4572671250361,0.380800029432784,-26.3191760127639,-1.4698455204969,6.46714742401116,-6.48662729989905,181.543308586919,17.4427644566622,1.12254126845791,16.8121282635185,2.19645079266969,-2.46516982244396,-64.7137644797539,25.5697325961502,10.1865885775772,2.93969541669645,34.6789068248076,2.00345628412319,-3.24345150456128,6.15238464185971,-14.3047335221127,6.12009819916686,-3.46097991255378,2.81603009029005,7.90842382640175,-1.11608813574009,-3.09059815711035,6.86979716790855,0.933565502414651,-12.4020933361081,-4.58792914120681,13.0302649040875,3.49719456405208,-7.81023467198432,8.86414675892974,11.2246323993736,-5.41992869821251,24.5909420721778,-185.000109685177,3.07587583861117,-4.30719839351012,-7.6092721706101,75.0354478823319,3.82684552324171,-18.1264009686712,-114.330816849666,-2.67928913679746,-9.83859232646713,-49.7389435115589,14.2576956251547,-5.61848936232265,108.499585309758,4.56384939024729,-4.66799519597577,107.340367876186,-11.232350766442,-6.14975789713083,-18.6774328612484,-17.9319705915071,9.20749559873331,-16.6729223658702,-0.162843619425797,14333.630356208,-9.21669421035372,-8.8255264543928,-0.716430671964001,-7.6384127207276,-43.5880285291762,2.35712704221201,-10.0807337415443,160.535958620933,1.00414769504982,-0.591513583172915,61.9869741094005,-114.828092291413,7.09859715260059,-5.39877881496041,8.00207082477325,-13.1492683272099,-392.474641910451,3.60300394534909,-2.71312637213858,7.1939464491766,-212.901670604981,1.16916667531336,-75.460091006712,-29.2313730225055,66.4762741498424,-29.0987096232183,-16.4760205960171,23.364402817196,-3.12932487174273,-17.4004907485781,-5.50630255748442,-8.57353813884174,5.56005391731231,6.59098736020489,-10.6484477517028,0.806806133684708,-0.833504380838438,2.33278168070829,5.07217905496544,6.78989280259503,-68.8478912198558,4.43572448279758,-22.6937390091186,7.4064116882175,17.0533411660505,-3.59894891186479,-55.8637916550365,-4.69845316313484,-111.342962551425,-21.4208062019931,37.5767846035553,-2.9254099755269,26.1821606273618,-10.6427853232249,-30.299826831852,-15.010180405855,-4.96637340938335,204.643460248734,-17.3619045201139,-93.5837428348249,30.9092077237078,-50.1816709033689,21.9090308830336,9.30097674400334
			);
}