# clojure - i18n

Simple internationalization for Clojure

Based on the projects:
 clji18n: :author "Sebastián Galkin"
 j18n     :author "Meikel Brandmeyer <m@kotka.de>"

It uses standard Java resource bundles to do the heavy-lifting
and as such integrates well with the Java infrastructure.


## Basic Usage

(use 'i18n.core)

Just call `i18n` with qualified keyword to retrieve a key from the
bundle specified by the namespace part of the keyword.

    (i18n ::my-message)
    (i18n ::aliased-namespace/my-message)
    (i18n :some.other.namespace/my-message)

The bundle name is derived from the namespace according to the usual
namespace name to file name translation rules.
    
You can add any number of arguments as replacements for message format placeholders.

    (i18n ::my-message "Rich" (java.util.Date.))
    
if the message in the resource bundle looks like so:
my-message=Hello {0}, today is {1,date,short} 



You may provide an explicit bundle:

    (i18n (ResourceBundle/getBundle "custom.package") ::my-message)
   


## Installation

Add [i18n "1.0.2"] to your project.clj

## License

Copyright (C) 2012 Peter Feldtmann

Distributed under the Eclipse Public License, the same as Clojure.
