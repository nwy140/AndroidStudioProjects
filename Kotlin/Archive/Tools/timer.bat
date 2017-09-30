




timeout 30


:timer
REM Activate Alarm
start chrome -app=http://www.infinitelooper.com/?v=_CVXWoVr9OU

REM start 1 minute
timeout 60

REM Continue

REM Activate Alarm 2
start chrome --app=http://www.infinitelooper.com/?v=SlZMVAydqaE&p=n


REM start 50 minute timer
timeout 3000

REM 10 minute
timeout 600


goto timer