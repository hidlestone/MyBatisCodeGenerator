package org.mybatis.generator.plugins.rename;


import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.plugins.util.UnderlineHumpTran;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 * This plugin demonstrates overriding the initialized() method to rename the
 * generated example classes. Instead of xxxExample, the classes will be named
 * xxxCriteria
 * <p>
 * This plugin accepts two properties:
 * <ul>
 * <li><tt>searchString</tt> (required) the regular expression of the name
 * search.</li>
 * <li><tt>replaceString</tt> (required) the replacement String.</li>
 * </ul>
 * <p>
 * For example, to change the name of the generated Example classes from
 * xxxExample to xxxCriteria, specify the following:
 * <p>
 * <dl>
 * <dt>searchString</dt>
 * <dd>Example$</dd>
 * <dt>replaceString</dt>
 * <dd>Criteria</dd>
 * </dl>
 *
 * @author Jeff Butler
 */
public class RenameSqlMapperPlugin extends PluginAdapter {
	private String searchString;
	private String replaceString;
	private Pattern pattern;


	public RenameSqlMapperPlugin() {
	}

	public boolean validate(List<String> warnings) {

		searchString = properties.getProperty("searchString"); //$NON-NLS-1$
		replaceString = properties.getProperty("replaceString"); //$NON-NLS-1$

		boolean valid = stringHasValue(searchString)
				&& stringHasValue(replaceString);

		if (valid) {
			pattern = Pattern.compile(searchString);
		} else {
			if (!stringHasValue(searchString)) {
				warnings.add(getString("ValidationError.18", //$NON-NLS-1$
						"RenameExampleClassPlugin", //$NON-NLS-1$
						"searchString")); //$NON-NLS-1$
			}
			if (!stringHasValue(replaceString)) {
				warnings.add(getString("ValidationError.18", //$NON-NLS-1$
						"RenameExampleClassPlugin", //$NON-NLS-1$
						"replaceString")); //$NON-NLS-1$
			}
		}

		return valid;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		String oldType = introspectedTable.getIbatis2SqlMapFileName();
		Matcher matcher = pattern.matcher(oldType);
		oldType = matcher.replaceAll(replaceString);
		if ("_SqlMap".equals(searchString)) {
			oldType = oldType.replace(searchString, "");
			oldType = oldType.replaceAll("\\s*", "");
			oldType = oldType.toLowerCase();
		}
		//下划线转驼峰命名
		oldType = UnderlineHumpTran.lineToHump(oldType);

		// 简单的方法
//		oldType = oldType.substring(0, 1).toUpperCase() + oldType.substring(1);
		// 效率高的方法
		char[] chars = oldType.toCharArray();
		if (chars[0] >= 'a' && chars[0] <= 'z') {
			chars[0] = (char) (chars[0] - 32);
		}
		oldType = String.valueOf(chars);//+Mapper
		oldType = oldType.subSequence(0,  oldType.indexOf("."))+"Mapper"+oldType.substring(oldType.indexOf("."));
		introspectedTable.setIbatis2SqlMapFileName(oldType);
	}

//    @Override
//    public void initialized(IntrospectedTable introspectedTable) {
//        String oldType = introspectedTable.getMyBatis3XmlMapperFileName();
//        Matcher matcher = pattern.matcher(oldType);
//        oldType = matcher.replaceAll(replaceString);
//        introspectedTable.setMyBatis3XmlMapperFileName(oldType);
//    }
}

