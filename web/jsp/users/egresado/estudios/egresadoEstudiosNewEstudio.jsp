<%--
    Document   : egresadoEstudiosNewEstudio
    Created on : 24-09-2014, 04:53:24 PM
    Author     : Álvaro Romero
--%>
<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="session.EgresadoSession" %>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Formulario de Creación de Nuevo Estudio</title>
        <link rel="stylesheet" href="/intranetv7/css/bootstrap/4.6.0/bootstrap.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/font-awesome-4.7.0/css/font-awesome.min.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-forms-validation.css" type="text/css" />
        <link rel="stylesheet" href="/intranetv7/css/local/local-project-3.0.1.css" type="text/css" />
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery-3.6.4.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/4.6.0/bootstrap.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/bootstrap/wait.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.validate.1.19.5.js"></script>
        <script type="text/javascript" src="/intranetv7/js/jquery/jquery.messages_es.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.custom.validation.methods-3.0.0.min.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/lib/lib.main-3.0.2.js"></script>
        <script type="text/javascript" src="/intranetv7/js/local/users/egresado/estudios/egresadoEstudiosNewEstudio-3.0.0.js"></script>
    </head>
        <%        
        EgresadoSession egresadoSession = (EgresadoSession) session.getAttribute("egresadoSession");
        Integer agno = egresadoSession.getAgno();
    %>
    <body class="inner-body">
        <div class="title-div">
            <s:text name="label.title.mis.estudios"/>
        </div>

        <div id="central-div">
            <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                <div class="errorBox">
                    <s:actionerror/><s:actionmessage/><s:fielderror/>
                </div>
            </s:if>
            <div class="buttons-div">
                <button id="save-button" name="save-button" class="btn btn-light" title="Grabar" type="button">
                    <span class="fa fa-save" aria-hidden="true"></span><span class="hidden-xs"> <s:text name="label.button.save"/></span>
                </button>
            </div>
            <div style="width: 100%; overflow: hidden">
                <form id="egresado-form" action="#" class="form-horizontal">

                    <div style="max-width:700px">
                        <h4><s:text name="label.study.information" /></h4>
                        <div class="form-group">
                            <label for="pais" class="col-sm-3 control-label"><s:text name="label.country" /></label>
                            <div class="col-sm-9">
                                <select id="pais" name="pais" class="form-control">
                                    <option value="4">AFGANISTAN</option>
                                    <option value="8">ALBANIA</option>
                                    <option value="280">ALEMANIA</option>
                                    <option value="854">ALTO VOLTA</option>
                                    <option value="294">ALTOS DEL GOLAN</option>
                                    <option value="20">ANDORRA</option>
                                    <option value="24">ANGOLA</option>
                                    <option value="10">ANTARTICA</option>
                                    <option value="28">ANTIGUA</option>
                                    <option value="532">ANTILLAS HOLANDESAS</option>
                                    <option value="682">ARABIA SAUDITA</option>
                                    <option value="12">ARGELIA</option>
                                    <option value="32">ARGENTINA</option>
                                    <option value="14">ARMENIA</option>
                                    <option value="36">AUSTRALIA</option>
                                    <option value="40">AUSTRIA</option>
                                    <option value="42">AZERBAIYAN</option>
                                    <option value="44">BAHAMAS</option>
                                    <option value="48">BAHREIN</option>
                                    <option value="50">BANGLADESH</option>
                                    <option value="52">BARBADOS</option>
                                    <option value="56">BELGICA</option>
                                    <option value="84">BELIZE</option>
                                    <option value="204">BENIN</option>
                                    <option value="60">BERMUDAS</option>
                                    <option value="64">BHUTAN</option>
                                    <option value="112">BIELORRUSIA</option>
                                    <option value="104">BIRMANIA</option>
                                    <option value="68">BOLIVIA</option>
                                    <option value="114">BOSNIA HERZEGOVINA</option>
                                    <option value="72">BOSTWANA</option>
                                    <option value="74">BOUVET, ISLA</option>
                                    <option value="76">BRASIL</option>
                                    <option value="96">BRUNEI</option>
                                    <option value="100">BULGARIA</option>
                                    <option value="108">BURUNDI</option>
                                    <option value="132">CABO VERDE</option>
                                    <option value="136">CAIMAN, ISLAS</option>
                                    <option value="120">CAMERUN</option>
                                    <option value="124">CANADA</option>
                                    <option value="128">CANTON Y ENDERBURY, ISLAS</option>
                                    <option value="148">CHAD</option>
                                    <option value="200">CHECOSLOVAQUIA</option>
                                    <option value="152">CHILE</option>
                                    <option value="156">CHINA</option>
                                    <option value="196">CHIPRE</option>
                                    <option value="162">CHRISTMAS, ISLA</option>
                                    <option value="146">CISJORDANIA</option>
                                    <option value="166">COCOS, ISLAS</option>
                                    <option value="170">COLOMBIA</option>
                                    <option value="174">COMORES</option>
                                    <option value="178">CONGO</option>
                                    <option value="184">COOK, ISLAS</option>
                                    <option value="408">COREA DEL NORTE</option>
                                    <option value="410">COREA DEL SUR</option>
                                    <option value="384">COSTA DE MARFIL</option>
                                    <option value="188">COSTA RICA</option>
                                    <option value="190">CROACIA</option>
                                    <option value="192">CUBA</option>
                                    <option value="208">DINAMARCA</option>
                                    <option value="262">DJIBUTI</option>
                                    <option value="212">DOMINICA</option>
                                    <option value="218">ECUADOR</option>
                                    <option value="818">EGIPTO</option>
                                    <option value="222">EL SALVADOR</option>
                                    <option value="784">EMIRATOS ARABES UNIDOS</option>
                                    <option value="228">ESLOVAQUIA</option>
                                    <option value="229">ESLOVENIA</option>
                                    <option value="724">ESPAÑA</option>
                                    <option value="227">ESTADO DE ERITREA</option>
                                    <option value="840">ESTADOS UNIDOS DE AMERICA</option>
                                    <option value="232">ESTONIA</option>
                                    <option value="230">ETIOPIA</option>
                                    <option value="234">FAEROES, ISLAS</option>
                                    <option value="242">FIDJI, ISLAS</option>
                                    <option value="608">FILIPINAS</option>
                                    <option value="246">FINLANDIA</option>
                                    <option value="250">FRANCIA</option>
                                    <option value="272">FRANJA DE GAZA</option>
                                    <option value="266">GABON</option>
                                    <option value="270">GAMBIA</option>
                                    <option value="274">GEORGIA</option>
                                    <option value="288">GHANA</option>
                                    <option value="292">GIBRALTAR</option>
                                    <option value="300">GRACIA</option>
                                    <option value="308">GRANADA</option>
                                    <option value="304">GROENLANDIA</option>
                                    <option value="312">GUADALUPE</option>
                                    <option value="316">GUAM</option>
                                    <option value="320">GUATEMALA</option>
                                    <option value="254">GUAYANA FRANCESA</option>
                                    <option value="324">GUINEA</option>
                                    <option value="226">GUINEA ECUATORIAL</option>
                                    <option value="624">GUINEA-BISSAU</option>
                                    <option value="328">GUYANA</option>
                                    <option value="332">HAITI</option>
                                    <option value="334">HEARD Y MCDONALD, ISLAS</option>
                                    <option value="528">HOLANDA</option>
                                    <option value="340">HONDURAS</option>
                                    <option value="344">HONG KONG</option>
                                    <option value="348">HUNGRIA</option>
                                    <option value="356">INDIA</option>
                                    <option value="86">INDICO, OCEANO (T. BRITANICO)</option>
                                    <option value="360">INDONESIA</option>
                                    <option value="364">IRAN</option>
                                    <option value="368">IRAQ</option>
                                    <option value="372">IRLANDA</option>
                                    <option value="352">ISLANDIA</option>
                                    <option value="376">ISRAEL</option>
                                    <option value="380">ITALIA</option>
                                    <option value="388">JAMAICA</option>
                                    <option value="392">JAPON</option>
                                    <option value="390">JERUSALEN</option>
                                    <option value="396">JOHNSTON, ISLA</option>
                                    <option value="400">JORDANIA</option>
                                    <option value="116">KAMPUCHEA</option>
                                    <option value="402">KAZAJSTAN</option>
                                    <option value="404">KENIA</option>
                                    <option value="406">KIRGUIZISTAN</option>
                                    <option value="296">KIRIBATI</option>
                                    <option value="414">KUWAIT</option>
                                    <option value="418">LAOS</option>
                                    <option value="426">LESOTHO</option>
                                    <option value="428">LETONIA</option>
                                    <option value="422">LIBANO</option>
                                    <option value="430">LIBERIA</option>
                                    <option value="434">LIBIA</option>
                                    <option value="438">LIECHTENSTEIN</option>
                                    <option value="440">LITUANIA</option>
                                    <option value="442">LUXEMBURGO</option>
                                    <option value="446">MACAO</option>
                                    <option value="450">MADAGASCAR</option>
                                    <option value="458">MALASIA</option>
                                    <option value="454">MALAWI</option>
                                    <option value="462">MALDIVAS, ISLAS</option>
                                    <option value="466">MALI</option>
                                    <option value="470">MALTA</option>
                                    <option value="238">MALVINAS, ISLAS</option>
                                    <option value="504">MARRUECOS</option>
                                    <option value="474">MARTINICA</option>
                                    <option value="480">MAURICIO</option>
                                    <option value="478">MAURITANIA</option>
                                    <option value="484">MEXICO</option>
                                    <option value="488">MIDWAY, ISLAS</option>
                                    <option value="494">MOLDAVIA</option>
                                    <option value="492">MONACO</option>
                                    <option value="496">MONGOLIA</option>
                                    <option value="500">MONTSERRAT</option>
                                    <option value="508">MOZAMBIQUE</option>
                                    <option value="999">NACION DESCONOCIDA</option>
                                    <option value="516">NAMIBIA</option>
                                    <option value="520">NAURU</option>
                                    <option value="524">NEPAL</option>
                                    <option value="558">NICARAGUA</option>
                                    <option value="562">NIGER, REPUBLICA DEL</option>
                                    <option value="566">NIGERIA</option>
                                    <option value="570">NIUE</option>
                                    <option value="574">NORFOLK, ISLA</option>
                                    <option value="578">NORUEGA</option>
                                    <option value="540">NUEVA CALEDONIA</option>
                                    <option value="554">NUEVA ZELANDIA</option>
                                    <option value="512">OMAN</option>
                                    <option value="582">PACIFICO, ISLAS</option>
                                    <option value="849">PACIFICO, ISLAS DEL (USA)</option>
                                    <option value="586">PAKISTAN</option>
                                    <option value="998">PALESTINA</option>
                                    <option value="590">PANAMA</option>
                                    <option value="598">PAPUA NUEVA GUINEA</option>
                                    <option value="600">PARAGUAY</option>
                                    <option value="604">PERU</option>
                                    <option value="612">PITCAIRN, ISLA</option>
                                    <option value="258">POLINESIA FRANCESA</option>
                                    <option value="616">POLONIA</option>
                                    <option value="620">PORTUGAL</option>
                                    <option value="630">PUERTO RICO</option>
                                    <option value="634">QATAR</option>
                                    <option value="216">REINA MAUD, TIERRA DE LA</option>
                                    <option value="826">REINO UNIDO</option>
                                    <option value="140">REPUBLICA CENTROAFRICANA</option>
                                    <option value="202">REPUBLICA CHECA</option>
                                    <option value="214">REPUBLICA DOMINICANA</option>
                                    <option value="638">REUNION</option>
                                    <option value="646">RUANDA</option>
                                    <option value="642">RUMANIA</option>
                                    <option value="810">RUSIA</option>
                                    <option value="812">RUSIA (FEDERACION DE RUSIA)</option>
                                    <option value="732">SAHARA OCCIDENTAL</option>
                                    <option value="658">SAINT-CHRISTOPHER, NIEVES Y ANG</option>
                                    <option value="90">SALOMON, ISLAS</option>
                                    <option value="882">SAMOA</option>
                                    <option value="16">SAMOA NORTEAMERICANA</option>
                                    <option value="674">SAN MARINO</option>
                                    <option value="666">SAN PEDRO Y MIQUELON</option>
                                    <option value="670">SAN VICENTE Y LAS GRANADINAS</option>
                                    <option value="654">SANTA ELENA</option>
                                    <option value="662">SANTA LUCIA</option>
                                    <option value="678">SANTO TOMAS Y PRINCIPE</option>
                                    <option value="686">SENEGAL</option>
                                    <option value="690">SEYCHELLES</option>
                                    <option value="694">SIERRA LEONA</option>
                                    <option value="698">SIKKIM</option>
                                    <option value="702">SINGAPUR</option>
                                    <option value="760">SIRIA</option>
                                    <option value="706">SOMALIA</option>
                                    <option value="144">SRI LANKA</option>
                                    <option value="710">SUDAFRICA</option>
                                    <option value="736">SUDAN</option>
                                    <option value="752">SUECIA</option>
                                    <option value="756">SUIZA</option>
                                    <option value="740">SURINAM</option>
                                    <option value="744">SVALBARD Y JAN MAYEN, ISLA</option>
                                    <option value="748">SWAZILANDIA</option>
                                    <option value="762">TADJIKISTAN</option>
                                    <option value="764">TAILANDIA</option>
                                    <option value="158">TAIWAN</option>
                                    <option value="834">TANZANIA</option>
                                    <option value="626">TIMOR</option>
                                    <option value="768">TOGO</option>
                                    <option value="772">TOKELAU</option>
                                    <option value="776">TONGA</option>
                                    <option value="780">TRINIDAD Y TOBAGO</option>
                                    <option value="788">TUNEZ</option>
                                    <option value="794">TURKMENISTAN</option>
                                    <option value="796">TURKS Y CAICOS, ISLAS</option>
                                    <option value="792">TURQUIA</option>
                                    <option value="798">TUVALU</option>
                                    <option value="804">UCRANIA</option>
                                    <option value="800">UGANDA</option>
                                    <option value="858">URUGUAY</option>
                                    <option value="851">UZBEKISTAN</option>
                                    <option value="548">VANUATU</option>
                                    <option value="336">VATICANO</option>
                                    <option value="862">VENEZUELA</option>
                                    <option value="704">VIETNAM</option>
                                    <option value="850">VIRGENES, ISLAS</option>
                                    <option value="92">VIRGENES, ISLAS (REINO UNIDO)</option>
                                    <option value="872">WAKE, ISLA DE</option>
                                    <option value="876">WALLIS Y FUTUNA, ISLAS</option>
                                    <option value="886">YEMEN DEL SUR</option>
                                    <option value="720">YEMEN DEMOCRATICO</option>
                                    <option value="890">YUGOSLAVIA</option>
                                    <option value="180">ZAIRE</option>
                                    <option value="894">ZAMBIA</option>
                                    <option value="716">ZIMBABWE</option>
                                    <option value="536">ZONA NEUTRA</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" id="institucionEducacionalDiv">
                            <label for="institucionEducacional" class="col-sm-3 control-label"><s:text name="label.institution.name" /></label>
                            <div class="col-sm-9">
                                <select id="institucionEducacional" name="institucionEducacional" class="form-control">
                                    <option value="0">-- Seleccione Institución --</option>
                                    <option value="1">CFT. Alexander Von Humboldt</option>
                                    <option value="2">CFT. ALFA</option>
                                    <option value="3">CFT. Alpes</option>
                                    <option value="4">CFT. Andrés Bello</option>
                                    <option value="5">CFT. Barros Arana ex Pitagoras</option>
                                    <option value="6">CFT. Cámara de Comercio de Santiago</option>
                                    <option value="7">CFT. CEDUC - UCN</option>
                                    <option value="8">CFT. CEITEC</option>
                                    <option value="9">CFT. CENCO</option>
                                    <option value="10">CFT. Centro de Enseñanza de Alta Costura Paulina Diard</option>
                                    <option value="11">CFT. Centro Tecnológico Superior INFOMED</option>
                                    <option value="12">CFT. CEPA de la III Región</option>
                                    <option value="13">CFT. CEPONAL</option>
                                    <option value="14">CFT. CRECIC</option>
                                    <option value="15">CFT. CROWNLIET</option>
                                    <option value="16">CFT. de ENAC o de los Establecimientos Nacionales de Educación Caritas-Chile</option>
                                    <option value="17">CFT. de la Industria Gráfica o CFT.  INGRAF</option>
                                    <option value="18">CFT. de Tarapacá</option>
                                    <option value="19">CFT. del Medio Ambiente</option>
                                    <option value="20">CFT. Diego Portales</option>
                                    <option value="22">CFT. DUOC UC</option>
                                    <option value="23">CFT. EDUCAP</option>
                                    <option value="24">CFT. Escuela Culinaria Francesa ECOLE</option>
                                    <option value="25">CFT. Escuela de Altos Estudios de la Comunicación y Educación EACE</option>
                                    <option value="26">CFT. Escuela de Artes Aplicadas Oficios del Fuego</option>
                                    <option value="27">CFT. Escuela de Interpretes INCENI</option>
                                    <option value="28">CFT. Escuela Superior de Administración de Negocios del Norte - ESANE DEL NORTE</option>
                                    <option value="29">CFT. Estudio Profesor Valero</option>
                                    <option value="30">CFT. Finning</option>
                                    <option value="31">CFT. FONTANAR</option>
                                    <option value="32">CFT. ICEL</option>
                                    <option value="33">CFT. INACAP ex INACAP Santiago Centro</option>
                                    <option value="34">CFT. Instituto Central de Capacitación Educacional ICCE</option>
                                    <option value="35">CFT. Instituto de Secretariado INSEC</option>
                                    <option value="36">CFT. Instituto INTEC</option>
                                    <option value="37">CFT. Instituto Superior Alemán de Comercio INSALCO</option>
                                    <option value="38">CFT. Instituto Superior de Estudios Jurídicos CANON</option>
                                    <option value="39">CFT. Instituto Técnológico de Chile - I.T.C.</option>
                                    <option value="40">CFT. IPROSEC</option>
                                    <option value="41">CFT. Javiera Carrera</option>
                                    <option value="42">CFT. Jorge Alvarez Echeverría</option>
                                    <option value="43">CFT. Juan Bohon</option>
                                    <option value="44">CFT. La Araucana</option>
                                    <option value="45">CFT. LAPLACE o de Estudios Superiores y Capacitación Profesional LAPLACE</option>
                                    <option value="46">CFT. Los Leones</option>
                                    <option value="47">CFT. Lota-Arauco</option>
                                    <option value="48">CFT. Luis Alberto Vera</option>
                                    <option value="49">CFT. Magnos</option>
                                    <option value="50">CFT. Manpower</option>
                                    <option value="51">CFT. Massachusetts</option>
                                    <option value="52">CFT. Osorno</option>
                                    <option value="53">CFT. PRODATA</option>
                                    <option value="54">CFT. PROFASOC</option>
                                    <option value="55">CFT. PROTEC</option>
                                    <option value="56">CFT. San Agustín de Talca</option>
                                    <option value="57">CFT. Santo Tomás</option>
                                    <option value="58">CFT. Simón Bolivar</option>
                                    <option value="59">CFT. SOEDUC Aconcagua</option>
                                    <option value="60">CFT. Teodoro Wickel Kluwen</option>
                                    <option value="61">CFT. U. Valpo (ex Eugenio González Rojas)</option>
                                    <option value="62">CFT. UCEVALPO</option>
                                    <option value="63">CFT. UDA</option>
                                    <option value="64">Instituto Profesional Adventista</option>
                                    <option value="65">Instituto Profesional Agrario Adolfo Matthei</option>
                                    <option value="66">Instituto Profesional Aiep</option>
                                    <option value="67">Instituto Profesional Alemán Wilhelm Von Humboldt</option>
                                    <option value="68">Instituto Profesional Alpes</option>
                                    <option value="69">Instituto Profesional Arturo Prat (ex ECACEC)</option>
                                    <option value="70">Instituto Profesional CAMPUS</option>
                                    <option value="71">Instituto Profesional Carlos Casanueva</option>
                                    <option value="72">Instituto Profesional CENAFOM</option>
                                    <option value="73">Instituto Profesional Chileno Norteamericano</option>
                                    <option value="74">Instituto Profesional Chileno-Britanico de Cultura</option>
                                    <option value="75">Instituto Profesional CIISA</option>
                                    <option value="76">Instituto Profesional de Arte y Comunicación ARCOS</option>
                                    <option value="77">Instituto Profesional de Chile (ex San Bartolomé)</option>
                                    <option value="78">Instituto Profesional de Ciencias de la Computación  Acuario Data</option>
                                    <option value="79">Instituto Profesional de Ciencias y Artes INCACEA</option>
                                    <option value="80">Instituto Profesional de Ciencias y Educación Helen Keller</option>
                                    <option value="81">Instituto Profesional de ENAC</option>
                                    <option value="82">Instituto Profesional de Los Ángeles</option>
                                    <option value="83">Instituto Profesional de Providencia</option>
                                    <option value="84">Instituto Profesional del Valle Central (ex I.P. del Maule)</option>
                                    <option value="85">Instituto Profesional Diego Portales</option>
                                    <option value="86">Instituto Profesional Dr. Virginio Gómez</option>
                                    <option value="87">Instituto Profesional DUOC UC</option>
                                    <option value="88">Instituto Profesional EATRI</option>
                                    <option value="89">Instituto Profesional Escuela de Cine de Chile</option>
                                    <option value="90">Instituto Profesional Escuela de Contadores Auditores de Santiago</option>
                                    <option value="91">Instituto Profesional Escuela Moderna de Música</option>
                                    <option value="92">Instituto Profesional Escuela Nacional de Relaciones Públicas</option>
                                    <option value="93">Instituto Profesional Esucomex</option>
                                    <option value="94">Instituto Profesional Hogar Catequístico</option>
                                    <option value="95">Instituto Profesional Inacap</option>
                                    <option value="96">Instituto Profesional Instituto de Estudios Bancarios Guillermo Subercaseaux</option>
                                    <option value="97">Instituto Profesional Instituto Internacional de Artes Culinarias y Servicios</option>
                                    <option value="98">Instituto Profesional Instituto Nacional del Fútbol</option>
                                    <option value="99">Instituto Profesional Instituto Superior de Artes y Ciencias de la Comunicación</option>
                                    <option value="100">Instituto Profesional La Araucana</option>
                                    <option value="101">Instituto Profesional Latinoamericano de Comercio Exterior</option>
                                    <option value="102">Instituto Profesional Libertador de Los Andes</option>
                                    <option value="103">Instituto Profesional Los Lagos (ex IP de Concepción)</option>
                                    <option value="104">Instituto Profesional Los Leones</option>
                                    <option value="105">Instituto Profesional Luis Galdames</option>
                                    <option value="106">Instituto Profesional Mar Futuro</option>
                                    <option value="107">Instituto Profesional PROJAZZ</option>
                                    <option value="108">Instituto Profesional Santo Tomás</option>
                                    <option value="109">Instituto Profesional Teatro la Casa</option>
                                    <option value="110">Instituto Profesional Vertical Instituto Profesional</option>
                                    <option value="111">Pontificia Universidad Católica de Chile</option>
                                    <option value="112">Pontificia Universidad Católica de Valparaíso</option>
                                    <option value="113">Universidad  Adolfo Ibáñez</option>
                                    <option value="114">Universidad Academia de Humanismo Cristiano</option>
                                    <option value="115">Universidad Adventista de Chile</option>
                                    <option value="116">Universidad Alberto Hurtado</option>
                                    <option value="117">Universidad Andrés Bello</option>
                                    <option value="118">Universidad Arturo Prat</option>
                                    <option value="119">Universidad Austral de Chile</option>
                                    <option value="120">Universidad Autonóma de Chile (ex. Universidad Autónoma del Sur)</option>
                                    <option value="121">Universidad Autónoma Indoamericana</option>
                                    <option value="122">Universidad Bernardo O'Higgins</option>
                                    <option value="123">Universidad Bolivariana</option>
                                    <option value="124">Universidad Católica Cardenal Raúl Silva Henríquez</option>
                                    <option value="125">Universidad Católica de la Santísima Concepción</option>
                                    <option value="126">Universidad Católica de Temuco</option>
                                    <option value="127">Universidad Católica del Maule</option>
                                    <option value="128">Universidad Católica del Norte (Ex U. del Norte)</option>
                                    <option value="129">Universidad Central de Chile</option>
                                    <option value="130">Universidad Chileno Británica de Cultura</option>
                                    <option value="131">Universidad Contemporánea</option>
                                    <option value="132">Universidad de Aconcagua</option>
                                    <option value="133">Universidad de Antofagasta</option>
                                    <option value="134">Universidad de Arte y Ciencias Sociales Arcis</option>
                                    <option value="135">Universidad de Artes, Ciencias y Comunicación Uniacc</option>
                                    <option value="136">Universidad de Atacama</option>
                                    <option value="137">Universidad de Chile</option>
                                    <option value="138">Universidad de Ciencias de  la Informática UCINF</option>
                                    <option value="139">Universidad de Concepción</option>
                                    <option value="140">Universidad de La Frontera</option>
                                    <option value="141">Universidad de La Serena</option>
                                    <option value="142">Universidad de Las Américas</option>
                                    <option value="143">Universidad de Los Andes</option>
                                    <option value="144">Universidad de Los Lagos</option>
                                    <option value="145">Universidad de Magallanes</option>
                                    <option value="146">Universidad de Playa Ancha de Ciencias de la Educación</option>
                                    <option value="147">Universidad de Puerto Varas</option>
                                    <option value="148">Universidad de Rancagua</option>
                                    <option value="149">Universidad de Santiago de Chile</option>
                                    <option value="150">Universidad de Talca</option>
                                    <option value="151">Universidad de Tarapacá</option>
                                    <option value="152">Universidad de Temuco</option>
                                    <option value="153">Universidad de Valparaíso</option>
                                    <option value="154">Universidad de Viña del Mar</option>
                                    <option value="155">Universidad del Bío-Bío</option>
                                    <option value="156">Universidad del Desarrollo</option>
                                    <option value="157">Universidad del Mar</option>
                                    <option value="158">Universidad del Pacífico</option>
                                    <option value="159">Universidad Diego Portales</option>
                                    <option value="160">Universidad Educares</option>
                                    <option value="161">Universidad Europea de Negocios</option>
                                    <option value="162">Universidad Finis Terrae</option>
                                    <option value="163">Universidad Francisco de Aguirre</option>
                                    <option value="164">Universidad Francisco de Vitoria Ex Alonso de Ovalle</option>
                                    <option value="165">Universidad Gabriela Mistral</option>
                                    <option value="166">Universidad Iberoamericana de Ciencias y Tecnología, UNICYT</option>
                                    <option value="167">Universidad Internacional Sek</option>
                                    <option value="168">Universidad José Santos Ossa</option>
                                    <option value="169">Universidad La Araucana</option>
                                    <option value="170">Universidad La República</option>
                                    <option value="171">Universidad Las Condes</option>
                                    <option value="172">Universidad Los Leones (ex Universidad Marítima de Chile)</option>
                                    <option value="173">Universidad Mariscal Sucre</option>
                                    <option value="174">Universidad Mayor</option>
                                    <option value="175">Universidad Metropolitana de Ciencias de la Educación</option>
                                    <option value="176">Universidad Miguel de Cervantes</option>
                                    <option value="177">Universidad Panamericana de Ciencias y Artes</option>
                                    <option value="178">Universidad Pedro de Valdivia (ex Mariano Egaña)</option>
                                    <option value="179">Universidad Real</option>
                                    <option value="180">Universidad Regional El Libertador</option>
                                    <option value="181">Universidad Regional San Marcos</option>
                                    <option value="182">Universidad San Andrés</option>
                                    <option value="183">Universidad San Sebastián</option>
                                    <option value="184">Universidad Santa Cruz de Triana Ex Leonardo da Vinci</option>
                                    <option value="185">Universidad Santo Tomás</option>
                                    <option value="186">Universidad Técnica Federico Santa María</option>
                                    <option value="187">Universidad Técnológica de Chile INACAP</option>
                                    <option value="188">Universidad Tecnológica Metropolitana (Ex I.P. de Santiago)</option>
                                    <option value="">Otra</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" id="otraInstitucionDiv">
                            <label for="otraInstitucion" class="col-sm-3 control-label"><s:text name="label.other.institution"/></label>
                            <div class="col-sm-9">
                                <input type="text" id="otraInstitucion" name="otraInstitucion" class="form-control" placeholder="Nombre de la Institución"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="tipoEstudio" class="col-sm-3 control-label"><s:text name="label.study.type"/></label>
                            <div class="col-sm-9">
                                <select id="tipoEstudio" name="tipoEstudio" class="form-control">
                                    <option value="0">-- Seleccione Tipo de Estudio --</option>
                                    <option value="10">Doctorado</option>
                                    <option value="20">Magíster</option>
                                    <option value="30">MBA</option>
                                    <option value="40">Postítulo</option>
                                    <option value="50">Pregrado</option>
                                    <option value="60">Diplomado</option>
                                    <option value="">Otro</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="nombreEstudio" class="col-sm-3 control-label"><s:text name="label.study.name"/></label>
                            <div class="col-sm-9">
                                <input type="text" id="nombreEstudio" name="nombreEstudio" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="desdeMes" class="col-sm-3 control-label"><s:text name="label.date.from"/></label>
                            <div class="col-sm-5">
                                <select id="desdeMes" name="desdeMes" class="form-control">
                                    <option value="1">Enero</option>
                                    <option value="2">Febrero</option>
                                    <option value="3">Marzo</option>
                                    <option value="4">Abril</option>
                                    <option value="5">Mayo</option>
                                    <option value="6">Junio</option>
                                    <option value="7">Julio</option>
                                    <option value="8">Agosto</option>
                                    <option value="9">Septiembre</option>
                                    <option value="10">Octubre</option>
                                    <option value="11">Noviembre</option>
                                    <option value="12">Diciembre</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="desdeAgno" name="desdeAgno" class="form-control">
                                    <% for (int i = agno; i > 1950; i--) {
                                            out.println("<option value=\"" + i + "\">" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="hastaMes" class="col-sm-3 control-label"><s:text name="label.date.to"/></label>
                            <div class="col-sm-5">
                                <select id="hastaMes" name="hastaMes" class="form-control">
                                    <option value="" selected="selected">--</option>
                                    <option value="1">Enero</option>
                                    <option value="2">Febrero</option>
                                    <option value="3">Marzo</option>
                                    <option value="4">Abril</option>
                                    <option value="5">Mayo</option>
                                    <option value="6">Junio</option>
                                    <option value="7">Julio</option>
                                    <option value="8">Agosto</option>
                                    <option value="9">Septiembre</option>
                                    <option value="10">Octubre</option>
                                    <option value="11">Noviembre</option>
                                    <option value="12">Diciembre</option>
                                </select>
                            </div>
                            <div class="col-sm-4">
                                <select id="hastaAgno" name="hastaAgno" class="form-control">
                                    <option value="" selected="selected">--</option>
                                    <% for (int i = agno; i > 1950; i--) {
                                            out.println("<option value=\"" + i + "\">" + i + "</option>");
                                        }
                                    %>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="estadoEstudio" class="col-sm-3 control-label"><s:text name="label.study.state"/></label>
                            <div class="col-sm-9">
                                <select id="estadoEstudio" name="estadoEstudio" class="form-control">
                                    <option value="0">-- Seleccione Estado del Estudio --</option>
                                    <option value="10">Incompleto</option>
                                    <option value="20">Cursando</option>
                                    <option value="30">Titulado</option>
                                    <option value="40">Egresado</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="areaEstudio" class="col-sm-3 control-label"><s:text name="label.study.area"/></label>
                            <div class="col-sm-9">
                                <select id="areaEstudio" name="areaEstudio" class="form-control">
                                    <option value="0">-- Seleccione Área Estudio --</option>
                                    <option value="1">Agricultura</option>
                                    <option value="2">Forestal</option>
                                    <option value="3">Agronomía</option>
                                    <option value="4">Alimentos y bebidas</option>
                                    <option value="5">Antropología</option>
                                    <option value="6">Arquitectura</option>
                                    <option value="7">Arte y Entretenimiento</option>
                                    <option value="8">Automotriz</option>
                                    <option value="9">Aviación</option>
                                    <option value="10">Bienes raíces</option>
                                    <option value="11">Biotecnología</option>
                                    <option value="12">Ciencias</option>
                                    <option value="13">Ciencias de la vida</option>
                                    <option value="14">Ciencias Físicas</option>
                                    <option value="15">Ciencias Naturales</option>
                                    <option value="16">Ciencias del Mar</option>
                                    <option value="17">Comunicación Estratégica</option>
                                    <option value="18">Comunicación / Medios de comunicación</option>
                                    <option value="19">Relaciones Públicas</option>
                                    <option value="20">Publicidad</option>
                                    <option value="21">Periodismo</option>
                                    <option value="22">Marketing</option>
                                    <option value="23">Construcción</option>
                                    <option value="24">Contabilidad</option>
                                    <option value="25">Ingeniería Industrial</option>
                                    <option value="26">Cosmética y Belleza</option>
                                    <option value="27">Defensa</option>
                                    <option value="28">Defensor público</option>
                                    <option value="29">Derecho</option>
                                    <option value="30">Derecho Comercial</option>
                                    <option value="31">Derecho Laboral</option>
                                    <option value="32">Derecho Tributario</option>
                                    <option value="33">Economía</option>
                                    <option value="34">Educación</option>
                                    <option value="35">Embalaje</option>
                                    <option value="36">Emprendimiento</option>
                                    <option value="37">Energía</option>
                                    <option value="38">Estrategia</option>
                                    <option value="39">Recursos Humanos (RR.HH.)</option>
                                    <option value="40">Fabricación</option>
                                    <option value="41">Familia</option>
                                    <option value="42">Farmacéutico</option>
                                    <option value="43">Filosofía</option>
                                    <option value="44">Finanzas</option>
                                    <option value="45">Finanzas / Banca</option>
                                    <option value="46">Capital de Riesgo</option>
                                    <option value="47">Finanzas Corporativas</option>
                                    <option value="48">Servicios financieros</option>
                                    <option value="49">Banca de Inversión</option>
                                    <option value="50">Finanzas / Bolsa</option>
                                    <option value="51">Fundación / Think Tank</option>
                                    <option value="52">Gobierno</option>
                                    <option value="53">Historia</option>
                                    <option value="54">Ingeniería</option>
                                    <option value="55">Ingeniería Aeronáutica</option>
                                    <option value="56">Ingeniería Bioquímica/Biomédica</option>
                                    <option value="57">Ingeniería Química</option>
                                    <option value="58">Ingeniería Civil</option>
                                    <option value="59">Ingeniería Informática</option>
                                    <option value="60">Ingeniería Eléctrica</option>
                                    <option value="61">Ingeniería del Medio Ambiente</option>
                                    <option value="62">Ingeniería Mecánica</option>
                                    <option value="63">Ingeniería Fabricación</option>
                                    <option value="64">Ingeniería Nuclear</option>
                                    <option value="65">Ingeniería en Plásticos y Materiales</option>
                                    <option value="66">Innovación</option>
                                    <option value="67">Internacional / ONG</option>
                                    <option value="68">Lenguaje</option>
                                    <option value="69">Investigación</option>
                                    <option value="70">Literatura</option>
                                    <option value="71">Matemáticas</option>
                                    <option value="72">MBA</option>
                                    <option value="73">MBA Contabilidad</option>
                                    <option value="74">MBA Finanzas</option>
                                    <option value="75">MBA Marketing</option>
                                    <option value="76">Medio Ambiente</option>
                                    <option value="77">Medio Ambiente - Conservación</option>
                                    <option value="78">Medio Ambiente - Ciencias del Medio Ambiente</option>
                                    <option value="79">Medio Ambiente - Pesca</option>
                                    <option value="80">Medio Ambiente - Suelos</option>
                                    <option value="81">Medio Ambiente - Recursos Naturales</option>
                                    <option value="82">Medio Ambiente - Residuos</option>
                                    <option value="83">Medio Ambiente - Vida silvestre</option>
                                    <option value="84">Minería</option>
                                    <option value="85">Moda</option>
                                    <option value="86">Organización sin fines de lucro</option>
                                    <option value="87">Outsourcing</option>
                                    <option value="88">Periodismo</option>
                                    <option value="89">Política</option>
                                    <option value="90">Políticas Públicas</option>
                                    <option value="91">Productos de consumo</option>
                                    <option value="92">Recreación / Deportes</option>
                                    <option value="93">Recursos Humanos</option>
                                    <option value="94">Religión / Teología</option>
                                    <option value="95">Retail</option>
                                    <option value="96">Salud</option>
                                    <option value="97">Salud - Administración</option>
                                    <option value="98">Salud - Tecnología</option>
                                    <option value="99">Salud - Entrenamiento Deportivo</option>
                                    <option value="100">Salud - Odontología</option>
                                    <option value="101">Salud - Investigación</option>
                                    <option value="102">Salud - Medicina</option>
                                    <option value="103">Salud - Salud Mental</option>
                                    <option value="104">Salud - Enfermería</option>
                                    <option value="105">Salud - Nutrición</option>
                                    <option value="106">Salud - Farmacia</option>
                                    <option value="107">Salud - Salud Pública</option>
                                    <option value="108">Salud - Veterinaria</option>
                                    <option value="109">Silvicultura</option>
                                    <option value="110">Sociología</option>
                                    <option value="111">Psicología</option>
                                    <option value="112">Tecnología</option>
                                    <option value="113">Administración de redes</option>
                                    <option value="114">Electrónica</option>
                                    <option value="115">Hardware</option>
                                    <option value="116">E-Commerce</option>
                                    <option value="117">Sistemas de Información</option>
                                    <option value="118">Software</option>
                                    <option value="119">Telecomunicaciones</option>
                                    <option value="120">Textiles</option>
                                    <option value="121">Transporte y logística</option>
                                    <option value="122">Tribunales</option>
                                    <option value="123">Ventas al por mayor</option>
                                    <option value="124">Viajes y Turismo</option>
                                    <option value="126">Negocios e Innovación</option>
                                    <option value="127">Administración Gerencial</option>
                                    <option value="128">Gestión Cultural</option>
                                    <option value="129">Historia del Arte</option>
                                </select>
                            </div>
                        </div>

                        <s:if test="hasFieldErrors() || hasActionErrors() || hasActionMessages()">
                            <div class="errorBox">
                                <s:actionerror/>
                                <s:actionmessage/>
                                <s:fielderror/>
                            </div>
                        </s:if>

                    </div>

                    <div id="hidden-input-div">
                        <input type="hidden" id="keyDummy" name="keyDummy" value="<s:property value="key"/>"/>
                        <input type="hidden" id="paisDummy" name="paisDummy" value="152"/>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>