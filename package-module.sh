MODULE=gateway
PS3='[Maven Package] Please enter your choice: '
options=("Quit" "auth" "gateway" "infrastructure" "file")
select opt in "${options[@]}"; do
  case $opt in
  "Quit")
    exit
    ;;
  "auth")
    MODULE=service/auth
    ;;
  "gateway")
    MODULE=gateway
    ;;
  "infrastructure")
    MODULE=service/infrastructure
    ;;
  "order")
    MODULE=service/order
    ;;
  *) echo invalid option ;;
  esac
  mvn clean package -pl $MODULE -am -Dmaven.test.skip=true
done

# mvn clean package -pl service/auth -am -Dmaven.test.skip=true
# mvn clean package -pl gateway --also-make -Dmaven.test.skip=true
