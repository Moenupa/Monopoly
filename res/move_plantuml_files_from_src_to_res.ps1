# this script
#   1. centralizes the plantuml files (seperated in different folders) to ./res/plantuml/
#   2. generates svgs and pngs to ./res/plantuml/png or ./res/plantuml/svg folder
# ! Before running the script, make sure you have run "Sketch It" IDEA plugin first


# assuming that the plantuml exists
# move all *.plantuml files to ./res/plantuml folder
cd ..
Get-Location
Get-ChildItem -Recurse -Include *.plantuml | Move-Item -Force -Destination ./res/plantuml

# based on the plantuml, generate svgs and pngs
# note that you must have downloaded the plantuml.jar package first
# be aware of the directory where the plantuml.jar package is
cd ./res/plantuml
Get-Location
java -jar C:/Github/plantuml.jar *.plantuml -o ./svg -tsvg
java -jar C:/Github/plantuml.jar *.plantuml -o ./png -tpng