#untar main file

tar -xvf ILSVRC2011_images_train.tar 

#untar everyclass to his directory

for pkg in n*.tar; do
   where="${pkg%.tar}/"
   dir=/home/luis/Base_de_datos/$where
   echo "$pkg"

   [ -d "$where" ] || mkdir "$where"

   tar -xf "$pkg" -C "$dir"
done
