# exchange-service

Objective of the application is to match supply with demand.

Supply and demand are managed through two separate priority queues having their own priority logic.
Supply follows the idea that lowest price must be at the top of the tree whereas demand follows the idea that highest price must be at the top of the tree.
These two heads must be matched to create the most profitable solution.

We have three interfaces to allow future extensions.
Reader to read data from a source.
Exchange to manage the supply and demand matching for an item
Writer to write data to any source.

These 3 interfaces are utilised in an ExchangeService class having a generic type.

One of the examples that we have used is to send implementation to this ExchangeService class.
These 3 implementations are 
StandardInputReader that reads from console.
MainExchange that follows the logic of priority queues.
StandardOutputWriter that writes into console.

There are unit tests to test logic residing in the code.
