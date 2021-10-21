cd ..
Get-Location
Get-ChildItem -Recurse -Include *.plantuml | Move-Item -Force -Destination ./res/plantuml
cd ./res/plantuml
Get-Location
java -jar C:/Github/plantuml.jar *.plantuml -o ./svg -tsvg
java -jar C:/Github/plantuml.jar *.plantuml -o ./png -tpng