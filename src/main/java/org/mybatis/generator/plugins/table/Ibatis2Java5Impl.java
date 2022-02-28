package org.mybatis.generator.plugins.table;

import org.mybatis.generator.codegen.ibatis2.IntrospectedTableIbatis2Java5Impl;

import java.text.MessageFormat;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * @Author payn
 * @Date 2019/9/19 22:15
 */
public class Ibatis2Java5Impl extends IntrospectedTableIbatis2Java5Impl {

//	@Override
//	protected String calculateIbatis2SqlMapFileName() {
//		StringBuilder sb = new StringBuilder();
//		if (stringHasValue(tableConfiguration.getMapperName())) {
//			String mapperName = tableConfiguration.getMapperName();
//			int ind = mapperName.lastIndexOf('.');
//			if (ind != -1) {
//				mapperName = mapperName.substring(ind + 1);
//			}
//			//支持mapperName = "{0}Dao" 等用法
//			sb.append(MessageFormat.format(mapperName, fullyQualifiedTable.getDomainObjectName()));
//			sb.append("Mapper.xml"); //$NON-NLS-1$
//		} else {
//			sb.append(fullyQualifiedTable.getDomainObjectName());
//			sb.append("Mapper.xml"); //$NON-NLS-1$
//		}
//		return sb.toString();
//	}

	@Override
	protected void calculateJavaClientAttributes() {
		if (context.getJavaClientGeneratorConfiguration() == null) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(calculateJavaClientImplementationPackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAOImpl"); //$NON-NLS-1$
		setDAOImplementationType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("DAO"); //$NON-NLS-1$
		setDAOInterfaceType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		if (stringHasValue(tableConfiguration.getMapperName())) {
			//支持mapperName = "{0}Dao" 等用法
			sb.append(MessageFormat.format(tableConfiguration.getMapperName(), fullyQualifiedTable.getDomainObjectName()));
			sb.append("Mapper");
		} else {
			sb.append(fullyQualifiedTable.getDomainObjectName());
			sb.append("Mapper"); //$NON-NLS-1$
		}
		setMyBatis3JavaMapperType(sb.toString());

		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		if (stringHasValue(tableConfiguration.getSqlProviderName())) {
			//支持mapperName = "{0}SqlProvider" 等用法
			sb.append(MessageFormat.format(tableConfiguration.getSqlProviderName(), fullyQualifiedTable.getDomainObjectName()));
			sb.append("Mapper");
		} else {
			sb.append(fullyQualifiedTable.getDomainObjectName());
			sb.append("SqlProvider"); //$NON-NLS-1$
		}
		setMyBatis3SqlProviderType(sb.toString());

		//10.自定义实体类名称
		//IntrospectedTable.java中calculateModelAttributes方法中修改如下：
		sb.setLength(0);
		sb.append(calculateJavaClientInterfacePackage());
		sb.append('.');
		sb.append("MiniappHaowu"); // add by zzx
		sb.append(fullyQualifiedTable.getDomainObjectName());
		sb.append("Po");//add by zzx
		setBaseRecordType(sb.toString());
	}
	
}
