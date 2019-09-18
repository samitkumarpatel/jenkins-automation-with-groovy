def text = '''
    <list>
        <technology>
          <name>${name}</name>
        </technology>
    </list>
'''
def binding = [ name: "samit"]

//process-1
def template = new groovy.text.StreamingTemplateEngine().createTemplate(text)
String response = template.make(binding)
println response

//process-2
def engine = new groovy.text.XmlTemplateEngine()
def xmlTemplate = engine.createTemplate(text).make(binding)
println xmlTemplate.toString()
