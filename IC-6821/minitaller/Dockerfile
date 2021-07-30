FROM ubuntu

WORKDIR /root
ENV HOME /root
RUN apt-get update -y && apt-get install -y curl zip unzip
RUN curl -s "https://get.sdkman.io" | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install java && sdk install gradle"

CMD ["/bin/bash"]
