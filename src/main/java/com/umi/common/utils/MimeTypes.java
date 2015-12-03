package com.umi.common.utils;

//Copyright (c) 2003-2009, Jodd Team (jodd.org). All Rights Reserved.

import java.util.HashMap;

/**
 * Map file extensions to MIME types. Based on the Apache mime.types file.
 * http://www.iana.org/assignments/media-types/
 */
public class MimeTypes
{
	public static final String MIME_APPLICATIOANDREW_INSET = "application/andrew-inset";
	public static final String MIME_APPLICATIOJSON = "application/json";
	public static final String MIME_APPLICATIOZIP = "application/zip";
	public static final String MIME_APPLICATIOX_GZIP = "application/x-gzip";
	public static final String MIME_APPLICATIOTGZ = "application/tgz";
	public static final String MIME_APPLICATIOMSWORD = "application/msword";
	public static final String MIME_APPLICATIOPOSTSCRIPT = "application/postscript";
	public static final String MIME_APPLICATIOPDF = "application/pdf";
	public static final String MIME_APPLICATIOJNLP = "application/jnlp";
	public static final String MIME_APPLICATIOMAC_BINHEX40 = "application/mac-binhex40";
	public static final String MIME_APPLICATIOMAC_COMPACTPRO = "application/mac-compactpro";
	public static final String MIME_APPLICATIOMATHML_XML = "application/mathml+xml";
	public static final String MIME_APPLICATIOOCTET_STREAM = "application/octet-stream";
	public static final String MIME_APPLICATIOODA = "application/oda";
	public static final String MIME_APPLICATIORDF_XML = "application/rdf+xml";
	public static final String MIME_APPLICATIOJAVA_ARCHIVE = "application/java-archive";
	public static final String MIME_APPLICATIORDF_SMIL = "application/smil";
	public static final String MIME_APPLICATIOSRGS = "application/srgs";
	public static final String MIME_APPLICATIOSRGS_XML = "application/srgs+xml";
	public static final String MIME_APPLICATIOVND_MIF = "application/vnd.mif";
	public static final String MIME_APPLICATIOVND_MSEXCEL = "application/vnd.ms-excel";
	public static final String MIME_APPLICATIOVND_MSPOWERPOINT = "application/vnd.ms-powerpoint";
	public static final String MIME_APPLICATIOVND_RNREALMEDIA = "application/vnd.rn-realmedia";
	public static final String MIME_APPLICATIOX_BCPIO = "application/x-bcpio";
	public static final String MIME_APPLICATIOX_CDLINK = "application/x-cdlink";
	public static final String MIME_APPLICATIOX_CHESS_PGN = "application/x-chess-pgn";
	public static final String MIME_APPLICATIOX_CPIO = "application/x-cpio";
	public static final String MIME_APPLICATIOX_CSH = "application/x-csh";
	public static final String MIME_APPLICATIOX_DIRECTOR = "application/x-director";
	public static final String MIME_APPLICATIOX_DVI = "application/x-dvi";
	public static final String MIME_APPLICATIOX_FUTURESPLASH = "application/x-futuresplash";
	public static final String MIME_APPLICATIOX_GTAR = "application/x-gtar";
	public static final String MIME_APPLICATIOX_HDF = "application/x-hdf";
	public static final String MIME_APPLICATIOX_JAVASCRIPT = "application/x-javascript";
	public static final String MIME_APPLICATIOX_KOAN = "application/x-koan";
	public static final String MIME_APPLICATIOX_LATEX = "application/x-latex";
	public static final String MIME_APPLICATIOX_NETCDF = "application/x-netcdf";
	public static final String MIME_APPLICATIOX_OGG = "application/x-ogg";
	public static final String MIME_APPLICATIOX_SH = "application/x-sh";
	public static final String MIME_APPLICATIOX_SHAR = "application/x-shar";
	public static final String MIME_APPLICATIOX_SHOCKWAVE_FLASH = "application/x-shockwave-flash";
	public static final String MIME_APPLICATIOX_STUFFIT = "application/x-stuffit";
	public static final String MIME_APPLICATIOX_SV4CPIO = "application/x-sv4cpio";
	public static final String MIME_APPLICATIOX_SV4CRC = "application/x-sv4crc";
	public static final String MIME_APPLICATIOX_TAR = "application/x-tar";
	public static final String MIME_APPLICATIOX_RAR_COMPRESSED = "application/x-rar-compressed";
	public static final String MIME_APPLICATIOX_TCL = "application/x-tcl";
	public static final String MIME_APPLICATIOX_TEX = "application/x-tex";
	public static final String MIME_APPLICATIOX_TEXINFO = "application/x-texinfo";
	public static final String MIME_APPLICATIOX_TROFF = "application/x-troff";
	public static final String MIME_APPLICATIOX_TROFF_MAN = "application/x-troff-man";
	public static final String MIME_APPLICATIOX_TROFF_ME = "application/x-troff-me";
	public static final String MIME_APPLICATIOX_TROFF_MS = "application/x-troff-ms";
	public static final String MIME_APPLICATIOX_USTAR = "application/x-ustar";
	public static final String MIME_APPLICATIOX_WAIS_SOURCE = "application/x-wais-source";
	public static final String MIME_APPLICATIOVND_MOZZILLA_XUL_XML = "application/vnd.mozilla.xul+xml";
	public static final String MIME_APPLICATIOXHTML_XML = "application/xhtml+xml";
	public static final String MIME_APPLICATIOXSLT_XML = "application/xslt+xml";
	public static final String MIME_APPLICATIOXML = "application/xml";
	public static final String MIME_APPLICATIOXML_DTD = "application/xml-dtd";
	public static final String MIME_IMAGE_BMP = "image/bmp";
	public static final String MIME_IMAGE_CGM = "image/cgm";
	public static final String MIME_IMAGE_GIF = "image/gif";
	public static final String MIME_IMAGE_IEF = "image/ief";
	public static final String MIME_IMAGE_JPEG = "image/jpeg";
	public static final String MIME_IMAGE_TIFF = "image/tiff";
	public static final String MIME_IMAGE_PNG = "image/png";
	public static final String MIME_IMAGE_SVG_XML = "image/svg+xml";
	public static final String MIME_IMAGE_VND_DJVU = "image/vnd.djvu";
	public static final String MIME_IMAGE_WAP_WBMP = "image/vnd.wap.wbmp";
	public static final String MIME_IMAGE_X_CMU_RASTER = "image/x-cmu-raster";
	public static final String MIME_IMAGE_X_ICON = "image/x-icon";
	public static final String MIME_IMAGE_X_PORTABLE_ANYMAP = "image/x-portable-anymap";
	public static final String MIME_IMAGE_X_PORTABLE_BITMAP = "image/x-portable-bitmap";
	public static final String MIME_IMAGE_X_PORTABLE_GRAYMAP = "image/x-portable-graymap";
	public static final String MIME_IMAGE_X_PORTABLE_PIXMAP = "image/x-portable-pixmap";
	public static final String MIME_IMAGE_X_RGB = "image/x-rgb";
	public static final String MIME_AUDIO_BASIC = "audio/basic";
	public static final String MIME_AUDIO_MIDI = "audio/midi";
	public static final String MIME_AUDIO_MPEG = "audio/mpeg";
	public static final String MIME_AUDIO_X_AIFF = "audio/x-aiff";
	public static final String MIME_AUDIO_X_MPEGURL = "audio/x-mpegurl";
	public static final String MIME_AUDIO_X_PREALAUDIO = "audio/x-pn-realaudio";
	public static final String MIME_AUDIO_X_WAV = "audio/x-wav";
	public static final String MIME_CHEMICAL_X_PDB = "chemical/x-pdb";
	public static final String MIME_CHEMICAL_X_XYZ = "chemical/x-xyz";
	public static final String MIME_MODEL_IGES = "model/iges";
	public static final String MIME_MODEL_MESH = "model/mesh";
	public static final String MIME_MODEL_VRLM = "model/vrml";
	public static final String MIME_TEXT_PLAIN = "text/plain";
	public static final String MIME_TEXT_RICHTEXT = "text/richtext";
	public static final String MIME_TEXT_RTF = "text/rtf";
	public static final String MIME_TEXT_HTML = "text/html";
	public static final String MIME_TEXT_CALENDAR = "text/calendar";
	public static final String MIME_TEXT_CSS = "text/css";
	public static final String MIME_TEXT_SGML = "text/sgml";
	public static final String MIME_TEXT_TAB_SEPARATED_VALUES = "text/tab-separated-values";
	public static final String MIME_TEXT_VND_WAP_XML = "text/vnd.wap.wml";
	public static final String MIME_TEXT_VND_WAP_WMLSCRIPT = "text/vnd.wap.wmlscript";
	public static final String MIME_TEXT_X_SETEXT = "text/x-setext";
	public static final String MIME_TEXT_X_COMPONENT = "text/x-component";
	public static final String MIME_VIDEO_QUICKTIME = "video/quicktime";
	public static final String MIME_VIDEO_MPEG = "video/mpeg";
	public static final String MIME_VIDEO_VND_MPEGURL = "video/vnd.mpegurl";
	public static final String MIME_VIDEO_X_MSVIDEO = "video/x-msvideo";
	public static final String MIME_VIDEO_X_MS_WMV = "video/x-ms-wmv";
	public static final String MIME_VIDEO_X_SGI_MOVIE = "video/x-sgi-movie";
	public static final String MIME_X_CONFERENCE_X_COOLTALK = "x-conference/x-cooltalk";

	private static HashMap<String, String> mimeTypeMapping;

	static
	{
		mimeTypeMapping = new HashMap<String, String>(200)
		{
			private void put1(String key, String value)
			{
				if (put(key, value) != null)
				{
					throw new IllegalArgumentException("Duplicated extension: "
							+ key);
				}
			}

			{
				put1("xul", MIME_APPLICATIOVND_MOZZILLA_XUL_XML);
				put1("json", MIME_APPLICATIOJSON);
				put1("ice", MIME_X_CONFERENCE_X_COOLTALK);
				put1("movie", MIME_VIDEO_X_SGI_MOVIE);
				put1("avi", MIME_VIDEO_X_MSVIDEO);
				put1("wmv", MIME_VIDEO_X_MS_WMV);
				put1("m4u", MIME_VIDEO_VND_MPEGURL);
				put1("mxu", MIME_VIDEO_VND_MPEGURL);
				put1("htc", MIME_TEXT_X_COMPONENT);
				put1("etx", MIME_TEXT_X_SETEXT);
				put1("wmls", MIME_TEXT_VND_WAP_WMLSCRIPT);
				put1("wml", MIME_TEXT_VND_WAP_XML);
				put1("tsv", MIME_TEXT_TAB_SEPARATED_VALUES);
				put1("sgm", MIME_TEXT_SGML);
				put1("sgml", MIME_TEXT_SGML);
				put1("css", MIME_TEXT_CSS);
				put1("ifb", MIME_TEXT_CALENDAR);
				put1("ics", MIME_TEXT_CALENDAR);
				put1("wrl", MIME_MODEL_VRLM);
				put1("vrlm", MIME_MODEL_VRLM);
				put1("silo", MIME_MODEL_MESH);
				put1("mesh", MIME_MODEL_MESH);
				put1("msh", MIME_MODEL_MESH);
				put1("iges", MIME_MODEL_IGES);
				put1("igs", MIME_MODEL_IGES);
				put1("rgb", MIME_IMAGE_X_RGB);
				put1("ppm", MIME_IMAGE_X_PORTABLE_PIXMAP);
				put1("pgm", MIME_IMAGE_X_PORTABLE_GRAYMAP);
				put1("pbm", MIME_IMAGE_X_PORTABLE_BITMAP);
				put1("pnm", MIME_IMAGE_X_PORTABLE_ANYMAP);
				put1("ico", MIME_IMAGE_X_ICON);
				put1("ras", MIME_IMAGE_X_CMU_RASTER);
				put1("wbmp", MIME_IMAGE_WAP_WBMP);
				put1("djv", MIME_IMAGE_VND_DJVU);
				put1("djvu", MIME_IMAGE_VND_DJVU);
				put1("svg", MIME_IMAGE_SVG_XML);
				put1("ief", MIME_IMAGE_IEF);
				put1("cgm", MIME_IMAGE_CGM);
				put1("bmp", MIME_IMAGE_BMP);
				put1("xyz", MIME_CHEMICAL_X_XYZ);
				put1("pdb", MIME_CHEMICAL_X_PDB);
				put1("ra", MIME_AUDIO_X_PREALAUDIO);
				put1("ram", MIME_AUDIO_X_PREALAUDIO);
				put1("m3u", MIME_AUDIO_X_MPEGURL);
				put1("aifc", MIME_AUDIO_X_AIFF);
				put1("aif", MIME_AUDIO_X_AIFF);
				put1("aiff", MIME_AUDIO_X_AIFF);
				put1("mp3", MIME_AUDIO_MPEG);
				put1("mp2", MIME_AUDIO_MPEG);
				put1("mp1", MIME_AUDIO_MPEG);
				put1("mpga", MIME_AUDIO_MPEG);
				put1("kar", MIME_AUDIO_MIDI);
				put1("mid", MIME_AUDIO_MIDI);
				put1("midi", MIME_AUDIO_MIDI);
				put1("dtd", MIME_APPLICATIOXML_DTD);
				put1("xsl", MIME_APPLICATIOXML);
				put1("xml", MIME_APPLICATIOXML);
				put1("xslt", MIME_APPLICATIOXSLT_XML);
				put1("xht", MIME_APPLICATIOXHTML_XML);
				put1("xhtml", MIME_APPLICATIOXHTML_XML);
				put1("src", MIME_APPLICATIOX_WAIS_SOURCE);
				put1("ustar", MIME_APPLICATIOX_USTAR);
				put1("ms", MIME_APPLICATIOX_TROFF_MS);
				put1("me", MIME_APPLICATIOX_TROFF_ME);
				put1("man", MIME_APPLICATIOX_TROFF_MAN);
				put1("roff", MIME_APPLICATIOX_TROFF);
				put1("tr", MIME_APPLICATIOX_TROFF);
				put1("t", MIME_APPLICATIOX_TROFF);
				put1("texi", MIME_APPLICATIOX_TEXINFO);
				put1("texinfo", MIME_APPLICATIOX_TEXINFO);
				put1("tex", MIME_APPLICATIOX_TEX);
				put1("tcl", MIME_APPLICATIOX_TCL);
				put1("sv4crc", MIME_APPLICATIOX_SV4CRC);
				put1("sv4cpio", MIME_APPLICATIOX_SV4CPIO);
				put1("sit", MIME_APPLICATIOX_STUFFIT);
				put1("swf", MIME_APPLICATIOX_SHOCKWAVE_FLASH);
				put1("shar", MIME_APPLICATIOX_SHAR);
				put1("sh", MIME_APPLICATIOX_SH);
				put1("cdf", MIME_APPLICATIOX_NETCDF);
				put1("nc", MIME_APPLICATIOX_NETCDF);
				put1("latex", MIME_APPLICATIOX_LATEX);
				put1("skm", MIME_APPLICATIOX_KOAN);
				put1("skt", MIME_APPLICATIOX_KOAN);
				put1("skd", MIME_APPLICATIOX_KOAN);
				put1("skp", MIME_APPLICATIOX_KOAN);
				put1("js", MIME_APPLICATIOX_JAVASCRIPT);
				put1("hdf", MIME_APPLICATIOX_HDF);
				put1("gtar", MIME_APPLICATIOX_GTAR);
				put1("spl", MIME_APPLICATIOX_FUTURESPLASH);
				put1("dvi", MIME_APPLICATIOX_DVI);
				put1("dxr", MIME_APPLICATIOX_DIRECTOR);
				put1("dir", MIME_APPLICATIOX_DIRECTOR);
				put1("dcr", MIME_APPLICATIOX_DIRECTOR);
				put1("csh", MIME_APPLICATIOX_CSH);
				put1("cpio", MIME_APPLICATIOX_CPIO);
				put1("pgn", MIME_APPLICATIOX_CHESS_PGN);
				put1("vcd", MIME_APPLICATIOX_CDLINK);
				put1("bcpio", MIME_APPLICATIOX_BCPIO);
				put1("rm", MIME_APPLICATIOVND_RNREALMEDIA);
				put1("ppt", MIME_APPLICATIOVND_MSPOWERPOINT);
				put1("mif", MIME_APPLICATIOVND_MIF);
				put1("grxml", MIME_APPLICATIOSRGS_XML);
				put1("gram", MIME_APPLICATIOSRGS);
				put1("smil", MIME_APPLICATIORDF_SMIL);
				put1("smi", MIME_APPLICATIORDF_SMIL);
				put1("rdf", MIME_APPLICATIORDF_XML);
				put1("ogg", MIME_APPLICATIOX_OGG);
				put1("oda", MIME_APPLICATIOODA);
				put1("dmg", MIME_APPLICATIOOCTET_STREAM);
				put1("lzh", MIME_APPLICATIOOCTET_STREAM);
				put1("so", MIME_APPLICATIOOCTET_STREAM);
				put1("lha", MIME_APPLICATIOOCTET_STREAM);
				put1("dms", MIME_APPLICATIOOCTET_STREAM);
				put1("bin", MIME_APPLICATIOOCTET_STREAM);
				put1("mathml", MIME_APPLICATIOMATHML_XML);
				put1("cpt", MIME_APPLICATIOMAC_COMPACTPRO);
				put1("hqx", MIME_APPLICATIOMAC_BINHEX40);
				put1("jnlp", MIME_APPLICATIOJNLP);
				put1("ez", MIME_APPLICATIOANDREW_INSET);
				put1("txt", MIME_TEXT_PLAIN);
				put1("ini", MIME_TEXT_PLAIN);
				put1("c", MIME_TEXT_PLAIN);
				put1("h", MIME_TEXT_PLAIN);
				put1("cpp", MIME_TEXT_PLAIN);
				put1("cxx", MIME_TEXT_PLAIN);
				put1("cc", MIME_TEXT_PLAIN);
				put1("chh", MIME_TEXT_PLAIN);
				put1("java", MIME_TEXT_PLAIN);
				put1("csv", MIME_TEXT_PLAIN);
				put1("bat", MIME_TEXT_PLAIN);
				put1("cmd", MIME_TEXT_PLAIN);
				put1("asc", MIME_TEXT_PLAIN);
				put1("rtf", MIME_TEXT_RTF);
				put1("rtx", MIME_TEXT_RICHTEXT);
				put1("html", MIME_TEXT_HTML);
				put1("htm", MIME_TEXT_HTML);
				put1("zip", MIME_APPLICATIOZIP);
				put1("rar", MIME_APPLICATIOX_RAR_COMPRESSED);
				put1("gzip", MIME_APPLICATIOX_GZIP);
				put1("gz", MIME_APPLICATIOX_GZIP);
				put1("tgz", MIME_APPLICATIOTGZ);
				put1("tar", MIME_APPLICATIOX_TAR);
				put1("gif", MIME_IMAGE_GIF);
				put1("jpeg", MIME_IMAGE_JPEG);
				put1("jpg", MIME_IMAGE_JPEG);
				put1("jpe", MIME_IMAGE_JPEG);
				put1("tiff", MIME_IMAGE_TIFF);
				put1("tif", MIME_IMAGE_TIFF);
				put1("png", MIME_IMAGE_PNG);
				put1("au", MIME_AUDIO_BASIC);
				put1("snd", MIME_AUDIO_BASIC);
				put1("wav", MIME_AUDIO_X_WAV);
				put1("mov", MIME_VIDEO_QUICKTIME);
				put1("qt", MIME_VIDEO_QUICKTIME);
				put1("mpeg", MIME_VIDEO_MPEG);
				put1("mpg", MIME_VIDEO_MPEG);
				put1("mpe", MIME_VIDEO_MPEG);
				put1("abs", MIME_VIDEO_MPEG);
				put1("doc", MIME_APPLICATIOMSWORD);
				put1("xls", MIME_APPLICATIOVND_MSEXCEL);
				put1("eps", MIME_APPLICATIOPOSTSCRIPT);
				put1("ai", MIME_APPLICATIOPOSTSCRIPT);
				put1("ps", MIME_APPLICATIOPOSTSCRIPT);
				put1("pdf", MIME_APPLICATIOPDF);
				put1("exe", MIME_APPLICATIOOCTET_STREAM);
				put1("dll", MIME_APPLICATIOOCTET_STREAM);
				put1("class", MIME_APPLICATIOOCTET_STREAM);
				put1("jar", MIME_APPLICATIOJAVA_ARCHIVE);
			}
		};
	}

	public static void main(String[] args)
	{
		System.out.println(mimeTypeMapping.size());
	}

	/**
	 * Registers MIME type for provided extension. Existing extension type will
	 * be overriden.
	 */
	public static void registerMimeType(String ext, String mimeType)
	{
		mimeTypeMapping.put(ext, mimeType);
	}

	/**
	 * Returns the corresponding MIME type to the given extension. If no MIME
	 * type was found it returns 'application/octet-stream' type.
	 */
	public static String getMimeType(String ext)
	{
		String mimeType = lookupMimeType(ext);
		if (mimeType == null)
		{
			mimeType = MIME_APPLICATIOOCTET_STREAM;
		}
		return mimeType;
	}

	/**
	 * Simply returns MIME type or <code>null</code> if no type is found.
	 */
	public static String lookupMimeType(String ext)
	{
		return mimeTypeMapping.get(ext.toLowerCase());
	}
}
