/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.dmn.xml;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.dmn.model.DmnDefinition;
import org.activiti.dmn.xml.converter.DmnXMLConverter;

public abstract class AbstractConverterTest {

  protected DmnDefinition readXMLFile() throws Exception {
    InputStream xmlStream = this.getClass().getClassLoader().getResourceAsStream(getResource());
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(xmlStream, "UTF-8");
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    return new DmnXMLConverter().convertToDmnModel(xtr);
  }

  protected DmnDefinition exportAndReadXMLFile(DmnDefinition definition) throws Exception {
    byte[] xml = new DmnXMLConverter().convertToXML(definition);
    System.out.println("xml " + new String(xml, "UTF-8"));
    XMLInputFactory xif = XMLInputFactory.newInstance();
    InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(xml), "UTF-8");
    XMLStreamReader xtr = xif.createXMLStreamReader(in);
    return new DmnXMLConverter().convertToDmnModel(xtr);
  }

  protected abstract String getResource();
}
