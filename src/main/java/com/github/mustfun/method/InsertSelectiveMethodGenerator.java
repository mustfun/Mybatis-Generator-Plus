/*
 * Copyright (c) 2018.  wuhandzy@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.mustfun.method;

import com.github.mustfun.utils.GenerateFilePackageHolder;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.logging.Log;
import org.mybatis.generator.logging.LogFactory;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2018/1/12
 * @since 1.0
 */
public class InsertSelectiveMethodGenerator extends AbstractJavaServiceMethodGenerator{

    private static final Log log= LogFactory.getLog(InsertSelectiveMethodGenerator.class);

    public InsertSelectiveMethodGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType type = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();
        method.setReturnType(type);
        importedTypes.add(type);

        FullyQualifiedJavaType reqType = new FullyQualifiedJavaType(GenerateFilePackageHolder.getFilePackage("req"));
        importedTypes.add(reqType);

        method.setName(introspectedTable.getInsertSelectiveStatementId());
        method.addParameter(new Parameter(reqType, "req"));

        addServiceAnnotations(interfaze, method);

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(
                method, interfaze, introspectedTable)) {
            addExtraImports(interfaze);
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addServiceAnnotations(Interface interfaze, Method method) {

    }

    public void addExtraImports(Interface interfaze) {
    }
}
