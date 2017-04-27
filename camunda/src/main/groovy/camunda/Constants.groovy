package camunda

interface Constants {

    def TYPE = "Process";
    def EXTENSION = "bpmn";
    def PROCESS_PATH = "grails-app/processes";

    /**
     * Generate proper package and name for a given full name
     * @param fullName the full name of the process definition
     */
    def generate = { String fullName ->
        def identifier = '[a-zA-Z_][a-zA-Z0-9_]*'
        def name = fullName - ~"\\.${EXTENSION}\$"
        assert name =~ "^(${identifier}\\.)*(${identifier})\$" : "The package and name of your " +
                "new process definition (name = $name) does not qualify as a valid Java identifier. For " +
                "compatibility reasons, please choose a name which qualifies as a valid Java class name " +
                "(including package)."
        [
                name.substring(0, name.contains('.') ? name.lastIndexOf('.') : 0),
                name.substring(name.lastIndexOf('.') + 1) - ~"$TYPE\$"
        ]
    }

}