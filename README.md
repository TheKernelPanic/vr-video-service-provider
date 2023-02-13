# VR Videos Service Provider 

___

### Description

Application server developed with spring boot.

### Docker environment (DB)

```bash 
docker-compose -p vr_videos_service_provider up -d 
```

### Build application via docker
```bash
docker run -it --rm -v "%cd%":/usr/src/workdir -v "%cd%/target:/usr/src/workdir/target" -w /usr/src/workdir maven mvn package -Dmaven.test.skip
```

### Variables VM

* __LOGS_PATH__ 