TIMEOUT=15
QUIET=0
STRICT=0

usage()
{
    echo "Usage: $0 host:port [-s] [-t timeout] [-- command args]"
    echo " -s  Strict mode, fail on the first error"
    echo " -q  Quiet mode, don't output any status messages"
    echo " -t  Timeout in seconds, zero for no timeout"
    exit 1
}

wait_for()
{
    for i in `seq $TIMEOUT` ; do
        nc -z $HOST $PORT > /dev/null 2>&1

        result=$?

        if [ $result -eq 0 ] ; then
            if [ $# -gt 0 ] ; then
                exec "$@"
            fi
            exit 0
        fi

        if [ $QUIET -ne 1 ] ; then
            echo "Waiting for $HOST:$PORT..."
        fi

        sleep 1
    done

    if [ $STRICT -eq 1 ] ; then
        echo "Timeout reached while waiting for $HOST:$PORT"
        exit 1
    fi

    echo "Timeout reached while waiting for $HOST:$PORT"
}

HOST=$(echo $1 | cut -d : -f 1)
PORT=$(echo $1 | cut -d : -f 2)

shift

while [ $# -gt 0 ]
do
    case "$1" in
        -q)
        QUIET=1
        shift
        ;;
        -s)
        STRICT=1
        shift
        ;;
        -t)
        TIMEOUT="$2"
        if [ "$TIMEOUT" = "" ]; then break; fi
        shift 2
        ;;
        --)
        shift
        break
        ;;
        --help)
        usage
        ;;
        *)
        break
        ;;
    esac
done

wait_for "$@"
