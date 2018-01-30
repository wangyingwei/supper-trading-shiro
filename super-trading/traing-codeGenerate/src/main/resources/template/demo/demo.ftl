<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.ibatis.type.Alias;

/**
 <#if table.remarks?exists && table.remarks != '' && table.remarks != 'null'>
 * ${table.remarks}
 </#if>
 * @author:admin
 * @version:1.0
 * @since:1.0
 * @createTime:<#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Alias("${classNameLower}")
@Entity(name = "${table.sqlName}")
public class ${className} implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    <#list table.columns as column>
    <#if column.remarks?exists && column.remarks != '' && column.remarks != 'null'>
    /** ${column.remarks} */
    </#if>
    private ${column.javaType} ${column.columnNameLower};

    </#list>    
    public ${className}(){
    }

    <#list table.columns as column>     
    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }

    <#if column.pk>
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    </#if>
    @Column(name = "${column.sqlName}")
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

    </#list>    
}