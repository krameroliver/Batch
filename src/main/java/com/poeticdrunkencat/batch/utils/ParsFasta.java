package com.poeticdrunkencat.batch.utils;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.compound.DNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.io.DNASequenceCreator;
import org.biojava.nbio.core.sequence.io.FastaReader;
import org.biojava.nbio.core.sequence.io.GenericFastaHeaderParser;
import org.biojava.nbio.core.util.InputStreamProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParsFasta {


    /***
     *
     * @param f path to fna file
     * @return Hashmap(FastaHeader, DNA Sequence)
     * @throws IOException
     */
    public Map<String, DNASequence> getGenom(File f) throws IOException {

        InputStreamProvider isp = new InputStreamProvider();
        InputStream inStream = null;
        try {
            inStream = isp.getInputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FastaReader<DNASequence, NucleotideCompound> fastaReader = new FastaReader<DNASequence, NucleotideCompound>(
                inStream,
                new GenericFastaHeaderParser<DNASequence, NucleotideCompound>(),
                new DNASequenceCreator(DNACompoundSet.getDNACompoundSet()));
        LinkedHashMap<String, DNASequence> genoms;
        int nrSeq = 0;
        genoms = fastaReader.process(100);


        return genoms;
    }


}
