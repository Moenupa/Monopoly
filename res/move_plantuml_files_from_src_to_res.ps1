# this script
#   1. centralizes the plantuml files (seperated in different folders) to ./res/plantuml/
#   2. generates svgs and pngs to ./res/plantuml/png or ./res/plantuml/svg folder
# ! Before running the script, make sure you have run "Sketch It" IDEA plugin first


# assuming that the plantuml exists
# move all *.plantuml files to ./res/plantuml folder
cd ..
Get-Location
Get-ChildItem -Recurse -Include *.plantuml | Move-Item -Force -Destination ./res/plantuml
 
# customize plantuml footer
cd ./res/plantuml
Get-Location
Get-Item *.plantuml | %{
    (gc $_) -replace "PlantUML diagram generated by SketchIt! (https://bitbucket.org/pmesmeur/sketch.it)`nFor more information about this tool, please contact philippe.mesmeur@gmail.com","COMP3211 Software Engineering Course Project: Monopoly, 2021 Fall`nby Group 22: MAN Furui, LIU Sicheng, WANG Meng, XING Shiji" | Set-Content $_.fullname
}

# based on the plantuml, generate svgs and pngs
# note that you must have downloaded the plantuml.jar package first
# be aware of the directory where the plantuml.jar package is
java -jar ../../../plantuml.jar *.plantuml -o ./svg -tsvg
java -jar ../../../plantuml.jar *.plantuml -o ./png -tpng