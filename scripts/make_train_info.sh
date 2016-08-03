FILE=train.txt
SYNSET_CODE_FILE=synset_code_file.txt

if [ -f $FILE ];
then
   echo "File $FILE exists. Removing $FILE."
   rm $FILE
fi

if [ -f $SYNSET_CODE_FILE ];
then
   echo "File $SYNSET_CODE_FILE exists. Removing $SYNSET_CODE_FILE."
   rm $SYNSET_CODE_FILE
fi

touch $FILE
touch $SYNSET_CODE_FILE
let COUNTER=0

for dir in n*/; do
	echo "$dir"
	echo "${dir%/} $COUNTER" >> $SYNSET_CODE_FILE
	for img in $dir*.JPEG; do
		echo "$img $COUNTER" >> $FILE
	done
	let COUNTER+=1
done

