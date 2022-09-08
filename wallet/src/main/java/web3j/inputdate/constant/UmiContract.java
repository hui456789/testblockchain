package web3j.inputdate.constant;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class UmiContract extends Contract {
    private static final String BINARY = "60a060405260405162000e1638038062000e168339810160408190526200002691620003cb565b806040518060400160405280600d81526020016c05374616e64617264455243323609c1b8152508487878160039080519060200190620000689291906200024b565b5080516200007e9060049060208401906200024b565b50505060ff166080526040516315b36b9760e11b81526001600160a01b03831690632b66d72e903490620000b79085906004016200047b565b6000604051808303818588803b158015620000d157600080fd5b505af1158015620000e6573d6000803e3d6000fd5b50505050505050600082116200014f5760405162461bcd60e51b8152602060048201526024808201527f5374616e6461726445524332303a20737570706c792063616e6e6f74206265206044820152637a65726f60e01b60648201526084015b60405180910390fd5b6200015b338362000166565b505050505062000514565b6001600160a01b038216620001be5760405162461bcd60e51b815260206004820152601f60248201527f45524332303a206d696e7420746f20746865207a65726f206164647265737300604482015260640162000146565b8060026000828254620001d29190620004b0565b90915550506001600160a01b0382166000908152602081905260408120805483929062000201908490620004b0565b90915550506040518181526001600160a01b038316906000907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a35050565b8280546200025990620004d7565b90600052602060002090601f0160209004810192826200027d5760008555620002c8565b82601f106200029857805160ff1916838001178555620002c8565b82800160010185558215620002c8579182015b82811115620002c8578251825591602001919060010190620002ab565b50620002d6929150620002da565b5090565b5b80821115620002d65760008155600101620002db565b634e487b7160e01b600052604160045260246000fd5b60005b83811015620003245781810151838201526020016200030a565b8381111562000334576000848401525b50505050565b600082601f8301126200034c57600080fd5b81516001600160401b0380821115620003695762000369620002f1565b604051601f8301601f19908116603f01168101908282118183101715620003945762000394620002f1565b81604052838152866020858801011115620003ae57600080fd5b620003c184602083016020890162000307565b9695505050505050565b600080600080600060a08688031215620003e457600080fd5b85516001600160401b0380821115620003fc57600080fd5b6200040a89838a016200033a565b965060208801519150808211156200042157600080fd5b5062000430888289016200033a565b945050604086015160ff811681146200044857600080fd5b6060870151608088015191945092506001600160a01b03811681146200046d57600080fd5b809150509295509295909350565b60208152600082518060208401526200049c81604085016020870162000307565b601f01601f19169190910160400192915050565b60008219821115620004d257634e487b7160e01b600052601160045260246000fd5b500190565b600181811c90821680620004ec57607f821691505b602082108114156200050e57634e487b7160e01b600052602260045260246000fd5b50919050565b6080516108e662000530600039600061011b01526108e66000f3fe608060405234801561001057600080fd5b50600436106100a95760003560e01c80633950935111610071578063395093511461014557806370a082311461015857806395d89b4114610181578063a457c2d714610189578063a9059cbb1461019c578063dd62ed3e146101af57600080fd5b806306fdde03146100ae578063095ea7b3146100cc57806318160ddd146100ef57806323b872dd14610101578063313ce56714610114575b600080fd5b6100b66101e8565b6040516100c39190610723565b60405180910390f35b6100df6100da366004610794565b61027a565b60405190151581526020016100c3565b6002545b6040519081526020016100c3565b6100df61010f3660046107be565b610290565b60405160ff7f00000000000000000000000000000000000000000000000000000000000000001681526020016100c3565b6100df610153366004610794565b61033f565b6100f36101663660046107fa565b6001600160a01b031660009081526020819052604090205490565b6100b661037b565b6100df610197366004610794565b61038a565b6100df6101aa366004610794565b610423565b6100f36101bd36600461081c565b6001600160a01b03918216600090815260016020908152604080832093909416825291909152205490565b6060600380546101f79061084f565b80601f01602080910402602001604051908101604052809291908181526020018280546102239061084f565b80156102705780601f1061024557610100808354040283529160200191610270565b820191906000526020600020905b81548152906001019060200180831161025357829003601f168201915b5050505050905090565b6000610287338484610430565b50600192915050565b600061029d848484610554565b6001600160a01b0384166000908152600160209081526040808320338452909152902054828110156103275760405162461bcd60e51b815260206004820152602860248201527f45524332303a207472616e7366657220616d6f756e74206578636565647320616044820152676c6c6f77616e636560c01b60648201526084015b60405180910390fd5b6103348533858403610430565b506001949350505050565b3360008181526001602090815260408083206001600160a01b0387168452909152812054909161028791859061037690869061088a565b610430565b6060600480546101f79061084f565b3360009081526001602090815260408083206001600160a01b03861684529091528120548281101561040c5760405162461bcd60e51b815260206004820152602560248201527f45524332303a2064656372656173656420616c6c6f77616e63652062656c6f77604482015264207a65726f60d81b606482015260840161031e565b6104193385858403610430565b5060019392505050565b6000610287338484610554565b6001600160a01b0383166104925760405162461bcd60e51b8152602060048201526024808201527f45524332303a20617070726f76652066726f6d20746865207a65726f206164646044820152637265737360e01b606482015260840161031e565b6001600160a01b0382166104f35760405162461bcd60e51b815260206004820152602260248201527f45524332303a20617070726f766520746f20746865207a65726f206164647265604482015261737360f01b606482015260840161031e565b6001600160a01b0383811660008181526001602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b6001600160a01b0383166105b85760405162461bcd60e51b815260206004820152602560248201527f45524332303a207472616e736665722066726f6d20746865207a65726f206164604482015264647265737360d81b606482015260840161031e565b6001600160a01b03821661061a5760405162461bcd60e51b815260206004820152602360248201527f45524332303a207472616e7366657220746f20746865207a65726f206164647260448201526265737360e81b606482015260840161031e565b6001600160a01b038316600090815260208190526040902054818110156106925760405162461bcd60e51b815260206004820152602660248201527f45524332303a207472616e7366657220616d6f756e7420657863656564732062604482015265616c616e636560d01b606482015260840161031e565b6001600160a01b038085166000908152602081905260408082208585039055918516815290812080548492906106c990849061088a565b92505081905550826001600160a01b0316846001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8460405161071591815260200190565b60405180910390a350505050565b600060208083528351808285015260005b8181101561075057858101830151858201604001528201610734565b81811115610762576000604083870101525b50601f01601f1916929092016040019392505050565b80356001600160a01b038116811461078f57600080fd5b919050565b600080604083850312156107a757600080fd5b6107b083610778565b946020939093013593505050565b6000806000606084860312156107d357600080fd5b6107dc84610778565b92506107ea60208501610778565b9150604084013590509250925092565b60006020828403121561080c57600080fd5b61081582610778565b9392505050565b6000806040838503121561082f57600080fd5b61083883610778565b915061084660208401610778565b90509250929050565b600181811c9082168061086357607f821691505b6020821081141561088457634e487b7160e01b600052602260045260246000fd5b50919050565b600082198211156108ab57634e487b7160e01b600052601160045260246000fd5b50019056fea26469706673582212203f149347dbf615dbabcbe7d171b7db32b0a098efab00277f224054851f60dcbc64736f6c634300080a003300000000000000000000000000000000000000000000000000000000000000a000000000000000000000000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000008000000000000000000000000000000000000000000000000002386f26fc10000000000000000000000000000997cd2e739fa7ce2bbec0d18181430bc23effa9700000000000000000000000000000000000000000000000000000000000000124558544f204469676974616c20546f6b656e000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000044558544f00000000000000000000000000000000000000000000000000000000";

    protected UmiContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected UmiContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public static RemoteCall<UmiContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_, BigInteger decimals_, BigInteger initialBalance_, String feeReceiver_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals_), 
                new Uint256(initialBalance_),
                new Address(feeReceiver_)));
        return deployRemoteCall(UmiContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<UmiContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name_, String symbol_, BigInteger decimals_, BigInteger initialBalance_, String feeReceiver_) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name_), 
                new org.web3j.abi.datatypes.Utf8String(symbol_), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals_), 
                new Uint256(initialBalance_),
                new Address(feeReceiver_)));
        return deployRemoteCall(UmiContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public RemoteCall<TransactionReceipt> allowance(String owner, String spender) {
        Function function = new Function(
                "allowance", 
                Arrays.<Type>asList(new Address(owner),
                new Address(spender)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        Function function = new Function(
                "approve", 
                Arrays.<Type>asList(new Address(spender),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> balanceOf(String account) {
        Function function = new Function(
                "balanceOf", 
                Arrays.<Type>asList(new Address(account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }
  public RemoteCall<BigInteger> balanceOf1(String who) {
    Function function = new Function("balanceOf",
      Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(who)),
      Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    return executeRemoteCallSingleValueReturn(function, BigInteger.class);
  }
    public RemoteCall<TransactionReceipt> decimals() {
        Function function = new Function(
                "decimals", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        Function function = new Function(
                "decreaseAllowance", 
                Arrays.<Type>asList(new Address(spender),
                new Uint256(subtractedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        Function function = new Function(
                "increaseAllowance", 
                Arrays.<Type>asList(new Address(spender),
                new Uint256(addedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> name() {
        Function function = new Function(
                "name", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> symbol() {
        Function function = new Function(
                "symbol", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> totalSupply() {
        Function function = new Function(
                "totalSupply", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String recipient, BigInteger amount) {
        Function function = new Function(
                "transfer", 
                Arrays.<Type>asList(new Address(recipient),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String sender, String recipient, BigInteger amount) {
        Function function = new Function(
                "transferFrom", 
                Arrays.<Type>asList(new Address(sender),
                new Address(recipient),
                new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static UmiContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new UmiContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static UmiContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new UmiContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }


  public static class ApprovalEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
