def names = ["a","b"]
def text = '''
    <list>
        <technology>
          <name>$name</name>
        </technology>
    </list>
'''

def engine = new groovy.text.XmlTemplateEngine()
names.each{
  def xmlTemplate = engine.createTemplate(text).make([name: it])
  println xmlTemplate.toString()
}
