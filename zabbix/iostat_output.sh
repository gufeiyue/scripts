iostat -xkd 2 2 |grep -E "\bvd[abcdefg]\b|\bsd[abcdefg]\b|\bhio[abcdefg]\b" >> /tmp/iostat_output
