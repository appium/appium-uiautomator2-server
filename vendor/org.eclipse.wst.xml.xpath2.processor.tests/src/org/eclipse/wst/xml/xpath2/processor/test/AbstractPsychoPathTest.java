/*******************************************************************************
 * Copyright (c) 2009, 2023 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *     Jin Mingjan - bug 262765 -  extractXPathExpression and getExpectedResults
 *     Jesper S Moller - bug 283214 - fix IF THEN ELSE parsing and update grammars
 *     Jesper S Moller - bug 283214 - fix XML result serialization
 *     Jesper S Moller - bug 283404 - fixed locale  
 *     Jesper S Moller - bug 281159 - fix document URIs and also filter XML namespace
 *     Jesper S Moller - bug 275610 - Avoid big time and memory overhead for externals
 *     Jesper Steen Moeller - bug 282096 - make test harness handle all string encoding
 *     Jesper Steen Moller  - bug 280555 - Add pluggable collation support
 *     Mukul Gandhi         - bug 338494 - prohibiting xpath expressions starting with / or // to be parsed.
 *     Jesper Steen Moller  - bug 340933 - Migrate tests to new XPath2 API
 *     Lukasz Wycisk - bug 361802 - Default variable namespace � no namespace
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.ProtectionDomain;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.xerces.jaxp.validation.XSGrammarPoolContainer;
import org.apache.xerces.xni.grammars.Grammar;
import org.apache.xerces.xni.grammars.XMLGrammarDescription;
import org.apache.xerces.xni.grammars.XMLGrammarPool;
import org.apache.xerces.xni.grammars.XSGrammar;
import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.XSConstants;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.custommonkey.xmlunit.XMLTestCase;
import org.eclipse.wst.xml.xpath2.api.CollationProvider;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.api.XPath2Expression;
import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.DOMLoader;
import org.eclipse.wst.xml.xpath2.processor.DOMLoaderException;
import org.eclipse.wst.xml.xpath2.processor.DefaultDynamicContext;
import org.eclipse.wst.xml.xpath2.processor.DefaultEvaluator;
import org.eclipse.wst.xml.xpath2.processor.DynamicContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.Engine;
import org.eclipse.wst.xml.xpath2.processor.Evaluator;
import org.eclipse.wst.xml.xpath2.processor.JFlexCupParser;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticChecker;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.StaticNameResolver;
import org.eclipse.wst.xml.xpath2.processor.XPathParser;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.XercesLoader;
import org.eclipse.wst.xml.xpath2.processor.ast.XPath;
import org.eclipse.wst.xml.xpath2.processor.function.FnFunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.function.XSCtrLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.DocType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.SimpleAtomicItemTypeImpl;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.userdefined.UserDefinedCtrLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.xerces.XercesTypeModel;
import org.eclipse.wst.xml.xpath2.processor.testsuite.userdefined.XercesFloatUserDefined;
import org.eclipse.wst.xml.xpath2.processor.testsuite.userdefined.XercesIntegerUserDefined;
import org.eclipse.wst.xml.xpath2.processor.testsuite.userdefined.XercesQNameUserDefined;
import org.eclipse.wst.xml.xpath2.processor.testsuite.userdefined.XercesUserDefined;
import org.eclipse.wst.xml.xpath2.processor.util.DynamicContextBuilder;
import org.eclipse.wst.xml.xpath2.processor.util.ResultSequenceUtil;
import org.eclipse.wst.xml.xpath2.processor.util.StaticContextBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.ext.EntityResolver2;

public class AbstractPsychoPathTest extends XMLTestCase {

	protected Document domDoc = null;
	protected Document domDoc2 = null;
	protected TestResourceBundle bundle = null;

	private DefaultDynamicContext dynamicContext = null;
	protected boolean useNewApi = true;
	private StaticContextBuilder staticContextBuilder;
	private DynamicContextBuilder dynamicContextBuilder;
	
	private static final String INPUT_CONTEXT = "input-context";
	private static final String INPUT_CONTEXT1 = "input-context1";
	private static final String INPUT_CONTEXT2 = "input-context2";
	// private static final String S_COMMENT1 = "(:";
	private static final String S_COMMENT2 = ":)";
	private static final String DECLARE_NAMESPACE = "declare namespace";
	private static final String IMPORT_SCHEMA_NAMESPACE = "import schema namespace";
	private static final String REGEX_DN = " namespace\\s+(\\w[-_\\w]*)\\s*=\\s*['\"]([^;]*)['\"];";

	private static HashMap inputMap = new HashMap(3);

	protected void setUp() throws Exception {
		super.setUp();
		bundle = TestResourceBundles.get("org.w3c.xqts.testsuite");

		if (bundle == null) {
			System.setProperty("javax.xml.parsers.DocumentBuilderFactory", "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
		}
		System.setProperty("javax.xml.validation.SchemaFactory:http://www.w3.org/2001/XMLSchema","org.apache.xerces.jaxp.validation.XMLSchemaFactory");
		
		staticContextBuilder = new StaticContextBuilder();
	}

	protected void loadDOMDocument(URL fileURL) throws IOException,
			DOMLoaderException {
		InputStream is = testResolve(fileURL);
		DOMLoader domloader = new XercesLoader();
		domloader.set_validating(false);
		domDoc = domloader.load(is);
		domDoc.setDocumentURI(fileURL.toString());
	}

	private InputStream testResolve(URL url) throws IOException {
		if (url.getProtocol().equals("http")) {
			return AbstractPsychoPathTest.class.getResourceAsStream("/org/eclipse/wst/xml/xpath2/processor/test/" + url.getFile());
		} else {
			return url.openStream();
		}
	}
	
	protected InputSource getTestSource(String systemId) {
		if (systemId.startsWith("http://")) {
			try {
				URL u = new URL(systemId);
				InputSource inputSource = new InputSource(testResolve(u));
				inputSource.setSystemId(systemId);
				return inputSource;
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return new InputSource(systemId);
	}
	
	protected EntityResolver makeTestResolver() {
		return new EntityResolver2() {
			
			public InputSource resolveEntity(String publicId, String systemId) {
				if (systemId.startsWith("http://")) {
					URL u;
					try {
						u = new URL(systemId);
						return new InputSource(testResolve(u));
					} catch (MalformedURLException e) {
						throw new RuntimeException(e);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
				return new InputSource(systemId);
			}

			public InputSource getExternalSubset(String publicId, String systemId)
					throws SAXException, IOException {
				return resolveEntity(publicId, systemId);
			}

			public InputSource resolveEntity(String name,
                    String publicId,
                    String baseURI,
                    String systemId) throws SAXException, IOException {
				return resolveEntity(publicId, systemId);
			}
			
		};
	}
	
	protected void loadDOMDocument(URL fileURL, Schema schema) throws IOException, DOMLoaderException {
		InputStream is = testResolve(fileURL);
		DOMLoader domloader = new XercesLoader(schema);
		domloader.set_validating(false);
		domDoc = domloader.load(is);
		domDoc.setDocumentURI(fileURL.toString());
		
	}
	
	protected void load2DOMDocument(URL fileURL, URL fileURL2) throws IOException,
			DOMLoaderException {
		InputStream is = testResolve(fileURL);
		InputStream is2 = testResolve(fileURL2);
		
		DOMLoader domloader = new XercesLoader();
		domloader.set_validating(false);
		domDoc = domloader.load(is);
		domDoc.setDocumentURI(fileURL.toString());
		domDoc2 = domloader.load(is2);
		domDoc2.setDocumentURI(fileURL2.toString());
		is.close();
		is2.close();
	}	

	protected void tearDown() throws Exception {
		super.tearDown();
		this.domDoc = null;
		this.domDoc2 = null;
		this.dynamicContext = null;
		this.dynamicContextBuilder = null;
		this.newXPath = null;
		this.oldXPath = null;
		this.staticContextBuilder = null;
	}

	protected XSModel getGrammar() {
		ElementPSVI rootPSVI = (ElementPSVI) domDoc.getDocumentElement();
		XSModel schema = rootPSVI.getSchemaInformation();
		return schema;
	}

	protected void loadDOMDocument(URL fileURL, URL schemaURL)
			throws IOException, DOMLoaderException, SAXException {
		InputStream is = testResolve(fileURL);
		InputStream schemaIs = testResolve(schemaURL);
		Schema jaxpSchema = getSchema(schemaIs);
		DOMLoader domloader = new XercesLoader(jaxpSchema);
		domloader.set_validating(false);
		domDoc = domloader.load(is);
	}
	
	

	private Schema getSchema(InputStream schemaIs) throws SAXException {
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new StreamSource(schemaIs));
		return schema;
	}

	protected XSModel getGrammar(URL schemaURL) throws IOException,
			SAXException {
		InputStream schemaIs = testResolve(schemaURL);
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new StreamSource(schemaIs));
		XSGrammarPoolContainer poolContainer = (XSGrammarPoolContainer) schema;
		XMLGrammarPool pool = poolContainer.getGrammarPool();
		Grammar[] grammars = pool
				.retrieveInitialGrammarSet(XMLGrammarDescription.XML_SCHEMA);

		XSGrammar[] xsGrammars = new XSGrammar[grammars.length];
		System.arraycopy(grammars, 0, xsGrammars, 0, grammars.length);

		return xsGrammars[0].toXSModel(xsGrammars);
	}


   protected void setVariable(String name, ResultSequence values) {
	   if (useNewApi) {
			staticContextBuilder.withVariable(new javax.xml.namespace.QName(
					name), new SimpleAtomicItemTypeImpl(BuiltinTypeLibrary.XS_ANYATOMICTYPE, ItemType.OCCURRENCE_NONE_OR_MANY));
		   dynamicContextBuilder.withVariable(new javax.xml.namespace.QName(name), values);
	   } else {
		   dynamicContext.set_variable(new QName(name), values);
	   }
   }

   protected void setVariable(String name, AnyType value) {
	   if (useNewApi) {
		   if (value != null) {
				staticContextBuilder.withVariable(new javax.xml.namespace.QName(
						name), new SimpleAtomicItemTypeImpl(value.getTypeDefinition(), ItemType.OCCURRENCE_ONE));
		   } else {		
				staticContextBuilder.withVariable(new javax.xml.namespace.QName( 
						name), new SimpleAtomicItemTypeImpl(BuiltinTypeLibrary.XS_UNTYPEDATOMIC, ItemType.OCCURRENCE_OPTIONAL));
		   }
		   dynamicContextBuilder.withVariable(new javax.xml.namespace.QName(name), value);
	   } else {
		   dynamicContext.set_variable(new QName(name), value);
	   }
   }
	
   void setCollationProvider(final CollationProvider cp) {
	   if (useNewApi) {
		   staticContextBuilder.withCollationProvider(cp);
	   } else {
		   dynamicContext.set_collation_provider(new org.eclipse.wst.xml.xpath2.processor.CollationProvider() {
			
			public Comparator get_collation(String name) {
				return cp.getCollation(name);
			}
		});
	   }
   }
   
	protected DefaultDynamicContext setupDynamicContext(XSModel schema) {
		XercesTypeModel typeModel = schema != null ? new XercesTypeModel(
				schema) : null;
		if (useNewApi) {
			staticContextBuilder.withTypeModel(typeModel);
			
			staticContextBuilder.withNamespace("xs", "http://www.w3.org/2001/XMLSchema");
			staticContextBuilder.withNamespace("xsd", "http://www.w3.org/2001/XMLSchema");
			staticContextBuilder.withNamespace("fn", "http://www.w3.org/2005/xpath-functions");
			staticContextBuilder.withNamespace("xml", "http://www.w3.org/XML/1998/namespace");
			
			dynamicContextBuilder = new DynamicContextBuilder(staticContextBuilder);
			setupVariables(dynamicContext);
			try {
				dynamicContextBuilder.withTimezoneOffset(DatatypeFactory.newInstance().newDuration(false/*i.e. negative*/, 0, 0, 0, 5, 0, 0));
			} catch (DatatypeConfigurationException e) {
				throw new RuntimeException("Shouldn't fail here", e);
			}
			return null;
		}
	
		DefaultDynamicContext dc = new DefaultDynamicContext(typeModel);
		dynamicContext = dc;

		dc.add_namespace("xs", "http://www.w3.org/2001/XMLSchema");
		dc.add_namespace("xsd", "http://www.w3.org/2001/XMLSchema");
		dc.add_namespace("fn", "http://www.w3.org/2005/xpath-functions");
		dc.add_namespace("xml", "http://www.w3.org/XML/1998/namespace");

		dc.add_function_library(new FnFunctionLibrary());
		dc.add_function_library(new XSCtrLibrary());
		setupVariables(dc);
		return dc;
	}

	protected void setCollections(Map map) {
		if (useNewApi) {
			dynamicContextBuilder.withCollections(map);
		} else {
			dynamicContext.set_collections(map);
		}
	}
	
	protected void addNamespace(String prefix, String nsURI) {
		if (useNewApi) {
			if (prefix == null || "".equals(prefix)) {
				staticContextBuilder.withDefaultNamespace(nsURI);
			} else {
				staticContextBuilder.withNamespace(prefix, nsURI);
			}
		} else {
			dynamicContext.add_namespace(prefix, nsURI);
		}
	}

	protected void addFunctionLibrary(FunctionLibrary fl) {
		if (useNewApi) {
			staticContextBuilder.withFunctionLibrary(fl.getNamespace(), fl);
		} else {
			dynamicContext.add_function_library(fl);
		}
	}

	protected void addXPathDefaultNamespace(String uri) {
	   addNamespace(null, uri);	
	}

	protected void setDefaultCollation(String uri) {
		if (useNewApi) {
			staticContextBuilder.withDefaultCollation(uri);
		} else {
			dynamicContext.set_default_collation(uri);
		}
		
	}

	protected XPath2Expression newXPath = null;
	private XPath oldXPath = null;
	
	protected XPath compileXPath( String xpath) throws XPathParserException, StaticError {
		if (useNewApi) {
			newXPath = new Engine().parseExpression(xpath, staticContextBuilder);
			return null;
		} else {
			XPathParser xpp = new JFlexCupParser();
			XPath path = oldXPath = xpp.parse(xpath);
			
			StaticChecker name_check = new StaticNameResolver(dynamicContext);
			name_check.check(path);
			return path;
		}
	}

	protected XPath compileXPath(String xpath, boolean isRootlessAccess) throws XPathParserException, StaticError {
       XPathParser xpp = new JFlexCupParser();
       XPath path = null; 
       if (isRootlessAccess) {
          path = xpp.parse(xpath, isRootlessAccess);
       }
       else {
    	   path = xpp.parse(xpath); 
       }
       
       StaticChecker name_check = new StaticNameResolver(dynamicContext);
       name_check.check(path);
       return path;
    }

	protected ResultSequence evaluate(Document domDoc) {
		if (useNewApi) {
			return ResultSequenceUtil.newToOld(newXPath.evaluate(dynamicContextBuilder, new Object[]{domDoc}));
		} else {
			Evaluator eval = new DefaultEvaluator(dynamicContext, domDoc);
			return eval.evaluate(oldXPath);
		}
	}

	protected String getExpectedResult(String xqFile) {
		return getExpectedResult(xqFile, true);
	}

	protected String getExpectedResultNoEscape(String xqFile) {
		return getExpectedResult(xqFile, false);
	}

	protected String getExpectedResult(String xqFile, boolean unescape) {
		String resultFile = xqFile;
		//
		if (resultFile.length() < 10) { // <9 enough? like XPST0001
			return resultFile;
		}
		String content = "";
		//
		InputStream isrf;
		try {
			isrf = bundle.getEntry(resultFile).openStream();
			BufferedReader rfreader = new BufferedReader(new InputStreamReader(
					isrf, "UTF-8"));
			// XXX:assume char buffer 2048 is long enough;1024 maybe enough
			// Exception: Axes085, NodeTest003/04/05,...
			int bufferLength = 2048;
			if ((resultFile.indexOf("Axes085") != -1)
					|| (resultFile.indexOf("NodeTest003") != -1)
					|| (resultFile.indexOf("NodeTest004") != -1)
					|| (resultFile.indexOf("NodeTest005") != -1)) {
				bufferLength = 40000;
			} else if (resultFile.indexOf("ForExpr013") != -1) {
				bufferLength = 433500;
			} else if (resultFile.indexOf("ForExpr016") != -1
					|| (resultFile.indexOf("ReturnExpr011") != -1)
					|| (resultFile.indexOf("sgml-queries-results-q1") != -1)
					|| (resultFile.indexOf("sgml-queries-results-q2") != -1)) {
				bufferLength = 10240;
			}
			char[] cbuf = new char[bufferLength];
			int nByte = rfreader.read(cbuf);
			assertTrue(resultFile, nByte < bufferLength);// assert nice buffer
			// length

			content = new String(cbuf).trim();
			rfreader.close();
			isrf.close();

		} catch (IOException e) {
			throw new RuntimeException("Can't load expected result from : " + xqFile, e);
		}
		if (unescape && content.contains("&")) return resolveCharacterReferences(content);
		return content;
	}

	public String unwrapResult(String expectedResult, String elemName) {
		return trimSurrounding(expectedResult, "<"+ elemName + ">", "</" + elemName + ">");
	}
	
	protected String getExpectedResult(String resultFile, String elemName) {
		return unwrapResult(getExpectedResult(resultFile), elemName);
	}

	public String extractXPathExpressionNoEscape(String xqFile, String inputFile) {
		return extractXPathExpression(xqFile, inputFile, false);
	}

	public String extractXPathExpression(String xqFile, String inputFile) {
		return extractXPathExpression(xqFile, inputFile, true);
	}

	public String extractXPathExpression(String xqFile, String inputFile, boolean unescape) {
		// get the xpath2 expr from xq file, first
		char[] cbuf = new char[2048];//
		String content = null;
		String xpath2Expr = null;

		try {
			URL entryUrl = bundle.getEntry(xqFile);
			InputStream isxq = testResolve(entryUrl);
			if (useNewApi) {
				if (staticContextBuilder.getBaseUri() == null)
					try {
						staticContextBuilder.withBaseUri(entryUrl.toString());
					} catch (URISyntaxException e) {
						throw new RuntimeException(e); // drats
					}
			} else {
				if (dynamicContext.base_uri().getStringValue() == null)
					dynamicContext.set_base_uri(entryUrl.toString());
			}
			BufferedReader xqreader = new BufferedReader(new InputStreamReader(
					isxq, Charset.forName("UTF-8")));
			int nByte = xqreader.read(cbuf);
			assertTrue(xqFile, nByte < 2048);
			content = new String(cbuf).trim();
			//
			if (content.indexOf(INPUT_CONTEXT) != -1
					&& content.indexOf(INPUT_CONTEXT1) == -1
					&& content.indexOf(INPUT_CONTEXT2) == -1) {
				inputMap.put(INPUT_CONTEXT, inputFile);
			} else if (content.indexOf(INPUT_CONTEXT1) == -1) {
				inputMap.put(INPUT_CONTEXT1, inputFile);
			} else if (content.indexOf(INPUT_CONTEXT2) != -1) {
				inputMap.put(INPUT_CONTEXT2, inputFile);
			}
			//	        
			if (content.indexOf(DECLARE_NAMESPACE) != -1 || content.indexOf(IMPORT_SCHEMA_NAMESPACE) != -1) {
				setupNamespace(content);
			}
			//
			assertTrue(content.lastIndexOf(S_COMMENT2) != -1);// assert to get
			xpath2Expr = content.substring(content.lastIndexOf(S_COMMENT2) + 2)
					.trim();			
			xqreader.close();
			isxq.close();
		} catch (IOException e) {
			throw new RuntimeException("Can't extract XPath expression from XQuery file : " + xqFile, e);
		}
		if (unescape && xpath2Expr.contains("&")) {
			return resolveCharacterReferences(xpath2Expr);
		} else {
			return xpath2Expr;
		}
	}

	protected String extractXPathExpression(String xqFile, String inputFile,
			String tagName) {
		return unwrapQuery(extractXPathExpression(xqFile, inputFile), tagName);
	}

	protected String unwrapQuery(String xpath2Expr, String tag) {
		String str = "<" + tag + ">";
		String endStr = "</" + tag + ">";
		String withoutTag = trimSurrounding(xpath2Expr, str, endStr);
		if (withoutTag != xpath2Expr) {
			// also trim off the braces { }
			xpath2Expr = trimSurrounding(withoutTag, "{", "}");
		}
		return xpath2Expr;
	}

	protected String trimSurrounding(String xpath2Expr, String str, String endStr) {
		int indexOfStart = xpath2Expr.indexOf(str);
		int indexOfEnd = xpath2Expr.indexOf(endStr);
		if (indexOfStart >= 0 && indexOfEnd >= 0) {
			xpath2Expr = xpath2Expr.substring(indexOfStart + str.length(), indexOfEnd).trim(); 
		}
		return xpath2Expr;
	}

	protected void setupNamespace(String content) {
		if (!useNewApi && dynamicContext == null) return; // Can't set it up if nonexistent
		Pattern p = Pattern.compile(REGEX_DN);
		Matcher m = p.matcher(content);
		while (m.find()) {
			assertTrue(m.groupCount() == 2);//
			addNamespace(m.group(1), m.group(2));
		}

	}

	protected DynamicContext setupVariables(DynamicContext dc) {
		setVariable("x", (AnyType)null);
		setVariable("var", (AnyType)null);
		
		if (domDoc != null) {
			TypeModel typeModel = dynamicContext != null ? dynamicContext.getTypeModel(domDoc) : staticContextBuilder.getTypeModel();
			AnyType docType = new DocType(domDoc, typeModel);
			setVariable("input-context1", docType);
			setVariable("input-context", docType);
			if (domDoc2 == null) {
				setVariable("input-context2", docType);
			} else {
				setVariable("input-context2", (AnyType) new DocType(domDoc2, typeModel));
			}
		}
		return dc;
	}

	protected void setBaseUri(String uri) throws URISyntaxException {
		if (useNewApi) {
			staticContextBuilder.withBaseUri(uri);
		} else {
			dynamicContext.set_base_uri(uri);
		}
	}
	
	protected String buildResultString(ResultSequence rs) {
		String actual = new String();
		Iterator iterator = rs.iterator();
		while (iterator.hasNext()) {
			AnyType anyType = (AnyType)iterator.next();
			
			actual = actual + anyType.getStringValue() + " ";
		}

		return actual.trim();
	}
	
	protected String buildXMLResultString(ResultSequence rs) throws Exception {
        DOMImplementationLS domLS = (DOMImplementationLS) domDoc.getImplementation().getFeature("LS", "3.0");
        LSOutput outputText = domLS.createLSOutput();
        LSSerializer serializer = null;
		ClassLoader originalLoader = Thread.currentThread().getContextClassLoader();
		DelegatingLoader newContext = new DelegatingLoader(originalLoader);
		Thread.currentThread().setContextClassLoader(newContext);
		try {
			serializer = domLS.createLSSerializer();
		} finally {
			Thread.currentThread().setContextClassLoader(originalLoader);
		}

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputText.setByteStream(outputStream);
        
		String actual = new String();
		Iterator iterator = rs.iterator();
		boolean queueSpace = false;
		while (iterator.hasNext()) {
			AnyType aat = (AnyType)iterator.next();
			if (aat instanceof NodeType) {
				NodeType nodeType = (NodeType) aat;
				Node node = nodeType.node_value();
//				assertTrue("Serializer " + serializer + " failed to serialize " + node.getClass().getName() + " " + node, serializer.write(node, outputText));
				if (!serializer.write(node, outputText)) {
					switch(node.getNodeType()) {
						case Node.COMMENT_NODE:
							outputText.getByteStream().write("<!--".getBytes("UTF-8"));
							outputText.getByteStream().write(node.getNodeValue().getBytes("UTF-8"));
							outputText.getByteStream().write("-->".getBytes("UTF-8"));
							break;
						case Node.PROCESSING_INSTRUCTION_NODE:
							outputText.getByteStream().write("<?".getBytes("UTF-8"));
							outputText.getByteStream().write(((ProcessingInstruction)node).getTarget().getBytes("UTF-8"));
							outputText.getByteStream().write(" ".getBytes("UTF-8"));
							outputText.getByteStream().write(node.getNodeValue().getBytes("UTF-8"));
							outputText.getByteStream().write("?>".getBytes("UTF-8"));
							break;
						default:
						outputText.getByteStream().write(node.getNodeValue().getBytes("UTF-8"));
					}
				}
				queueSpace = false;
			} else {
				if (queueSpace) outputText.getByteStream().write(32);
				outputText.getByteStream().write(aat.getStringValue().getBytes("UTF-8"));
				queueSpace = true;
			}
		}

		actual = outputStream.toString("UTF-8");
		actual = actual.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n", "");
		actual = actual.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
		actual = actual.replace("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n", "");
		actual = actual.replace("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>", "");
		outputStream.close();
		return actual.trim();
	}
	
	// org/apache/xml/serializer/Encodings.properties
	private static class DelegatingLoader extends ClassLoader {
		private ClassLoader delegate;
		private String ENCODINGS_FILE = "org/apache/xml/serializer/Encodings.properties";
		private String stream = null;
		public DelegatingLoader(ClassLoader del) {
			super(del.getParent());
			this.delegate = del;
		}
		public Class loadClass(String name) throws ClassNotFoundException {
			return delegate.loadClass(name);
		}
		public InputStream getResourceAsStream(String name) {
			if( ENCODINGS_FILE.equals(name)) {
				TestResourceBundle xpath2ProcTests = TestResourceBundles
						.get("org.eclipse.wst.xml.xpath2.processor.tests");
				try {
					return xpath2ProcTests.getEntry("bugTestFiles/Encodings.properties").openStream();
				} catch (IOException e) {
					System.err.println("Loading encodings failed.");
					e.printStackTrace();
					return null;
				}
			} else {
				return delegate.getResourceAsStream(name);
			}
		}

				
	}
	
	protected String formatResultString(String resultFile) throws Exception {
		DOMLoader domloader = new XercesLoader(null);
		domloader.set_validating(false);
		InputStream is = bundle.getEntry(resultFile).openStream();		
		Document resultDoc = domloader.load(is);

        DOMImplementationLS domLS = (DOMImplementationLS) resultDoc.getImplementation().getFeature("LS", "3.0");
        LSSerializer serializer = domLS.createLSSerializer();
        
		ClassLoader originalLoader = Thread.currentThread().getContextClassLoader();
		DelegatingLoader newContext = new DelegatingLoader(originalLoader);
		Thread.currentThread().setContextClassLoader(newContext);
		String actual = null;
		try {
	        actual = serializer.writeToString(resultDoc.getDocumentElement());
			actual = actual.replace("<?xml version=\"1.0\" encoding=\"UTF-16\"?>", "");
			return actual.trim();
		} finally {
			Thread.currentThread().setContextClassLoader(originalLoader);
		}
	}

	protected String removeIrrelevantNamespaces(String expectedResult) {
		expectedResult = expectedResult.replaceAll(" xmlns:xml=\"http://www.w3.org/XML/1998/namespace\"", "");
		expectedResult = expectedResult.replaceAll(" xmlns:foo=\"http://www.example.com/foo\"", "");
	    expectedResult = expectedResult.replaceAll(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
		return expectedResult;
	}

	protected String resolveCharacterReferences(String xpath) {
		String docText = "<doc>" + xpath + "</doc>";
		InputStream is;
		try {
			is = new ByteArrayInputStream(docText.getBytes("UTF-8"));
			DOMLoader domloader = new XercesLoader();
			domloader.set_validating(false);
			Document temp = domloader.load(is);
			return temp.getDocumentElement().getFirstChild().getTextContent();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (DOMLoaderException e) {
			throw new RuntimeException(e);
		}
	}

	protected void addUserDefinedSimpleTypes(XSModel schema) {
	      XSNamedMap xstypes = schema.getComponents(XSConstants.TYPE_DEFINITION);
	      if (xstypes.getLength() == 0) {
	    	  return;
	      }
	      
	      addNamespace("myType", "http://www.w3.org/XQueryTest/userDefinedTypes");
	      UserDefinedCtrLibrary udl = new UserDefinedCtrLibrary("http://www.w3.org/XQueryTest/userDefinedTypes");
	      
	      for (int i = 0; i < xstypes.getLength(); i++) {
	    	  XSObject xsobject = xstypes.item(i);
	    	  if ("http://www.w3.org/XQueryTest/userDefinedTypes".equals(xsobject.getNamespace())) {
	    		  if (xsobject instanceof XSSimpleTypeDefinition) {
	    			XSSimpleTypeDefinition typeDef = (XSSimpleTypeDefinition) xsobject;
					if (typeDef.getNumeric()) {
	    				  if (xsobject.getName().equals("floatBased") || xsobject.getName().equals("shoesize")) {
		    				  XercesFloatUserDefined fudt = new XercesFloatUserDefined(xsobject);
		    				  udl.add_type(fudt);
	    					  
	    				  } else {
		    				  XercesIntegerUserDefined iudt = new XercesIntegerUserDefined(xsobject);
		    				  udl.add_type(iudt);
	    				  }
	    			  }  else {
	    				  if (xsobject.getName().equals("QNameBased")) {
	    					  XercesQNameUserDefined qudt = new XercesQNameUserDefined(xsobject);
	    					  udl.add_type(qudt);
	    				  } else {
							XercesUserDefined udt = new XercesUserDefined(typeDef);
							udl.add_type(udt);
	    				  }
	    			  }
	    		  }
	    	  }
	      }
	      
	      addFunctionLibrary(udl);
	 
	   }

	protected void assertXPathTrue(String xpath, Document domDoc) {
		XSBoolean result = evaluateBoolXPath(xpath, domDoc);
		assertEquals(true, result.value());
	}

	protected XSBoolean evaluateBoolXPath(String xpath, Document doc) {
		return  (XSBoolean) evaluateSimpleXPath(xpath, doc, XSBoolean.class);
	}
		
	protected AnyType evaluateSimpleXPath(String xpath, Document doc, Class resultClass) {
		try {
			compileXPath(xpath);
		}
		catch (XPathParserException e) {
			throw new RuntimeException("XPath parse: " + e.getMessage(), e);
		}
		catch (StaticError e) {
			throw new RuntimeException("Static error: " + e.getMessage(), e);
		}
	
		ResultSequence rs;
		try {
			rs = evaluate(domDoc);
		}
		catch (DynamicError e) {
			throw new RuntimeException("Evaluation error: " + e.getMessage(), e);
		}
		assertEquals("Expected single result from \'" + xpath + "\'", 1, rs.size());
		
		AnyType result = rs.first();
		assertTrue("Exected XPath result instanceof class " + resultClass.getSimpleName() + " from \'" + xpath + "\', got " + result.getClass(), resultClass.isInstance(result));
		
		return result;
	}

	protected StaticContext getStaticContext() {
		return staticContextBuilder;
	}


}
